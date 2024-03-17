package com.beomstagram.feed.application.service;

import com.beomstagram.common.annotation.UseCase;
import com.beomstagram.feed.adapter.out.event.UploadImageRollbackEvent;
import com.beomstagram.feed.adapter.out.persistence.FeedEntity;
import com.beomstagram.feed.adapter.out.persistence.FeedImageEntity;
import com.beomstagram.feed.adapter.out.persistence.FeedMapper;
import com.beomstagram.feed.adapter.out.service.UploadedS3Image;
import com.beomstagram.feed.application.port.in.PostFeedCommand;
import com.beomstagram.feed.application.port.in.PostFeedUseCase;
import com.beomstagram.feed.application.port.out.FindUserPort;
import com.beomstagram.feed.application.port.out.UploadImagePort;
import com.beomstagram.feed.application.port.out.PostFeedPort;
import com.beomstagram.feed.domain.Feed;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@UseCase
@Transactional
@RequiredArgsConstructor
public class PostFeedService implements PostFeedUseCase {

    private final PostFeedPort postFeedPort;
    private final UploadImagePort uploadImagePort;
    private final FindUserPort findUserPort;
    private final FeedMapper feedMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Feed postFeed(PostFeedCommand command) {

        // 1. user가 존재하는지 확인
        if(!findUserPort.isExistsUser(command.getUserId())) {
            // todo : custom exception
            throw new RuntimeException();
        }

        // 2. image들을 먼저 S3에 생성한다.
        List<UploadedS3Image> s3Images = uploadImage(command.getImages());

        // 3. 업로드된 이미지 파일을 토대로 Event 생성(rollback 을 위한 event)
        eventPublisher.publishEvent(UploadImageRollbackEvent.rollbackFeedImagesEvent(s3Images));

        // 4. s3에 업로드한 image 정보를 토대로 feedImageEntity 생성
        List<FeedImageEntity> feedImageEntityList = createFeedImageEntity(s3Images, command.getHashtags());

        // 피드 생성
        FeedEntity feedEntity = postFeedPort.postFeed(command.getUserId(), feedImageEntityList, command.getCaption());
        return feedMapper.mapToDomain(feedEntity);
    }

    private List<UploadedS3Image> uploadImage(List<MultipartFile> images) {
        // 모든 이미지 파일을 S3에 업로드 하는 로직
        return images.stream()
                .map(uploadImagePort::uploadFeedImage)
                .toList();
    }

    private List<FeedImageEntity> createFeedImageEntity(List<UploadedS3Image> feedImages, List<List<String>> hashtags) {

        // todo : exception - 업로드를 위한 image 개수와 해쉬태그 리스트의 개수가 다르다면 => 잘못된 입력
        if(feedImages.size() != hashtags.size()) {
            throw new RuntimeException();
        }

        return IntStream.range(0, feedImages.size())
                .mapToObj(i -> {
                    UploadedS3Image image = feedImages.get(i);
                    List<String> hashtag = hashtags.get(i);
                    return FeedImageEntity.builder()
                            .imageName(image.getImageName())
                            .imageUrl(image.getImageUrl())
                            .hashtags(hashtag)
                            .build();
                }).toList();
    }
}
