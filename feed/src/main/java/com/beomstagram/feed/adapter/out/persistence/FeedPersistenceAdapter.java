package com.beomstagram.feed.adapter.out.persistence;

import com.beomstagram.common.annotation.PersistenceAdapter;
import com.beomstagram.feed.application.port.out.FindFeedPort;
import com.beomstagram.feed.application.port.out.PostFeedPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@PersistenceAdapter
@RequiredArgsConstructor
public class FeedPersistenceAdapter implements PostFeedPort, FindFeedPort {

    private final FeedRepository feedRepository;

    @Override
    public FeedEntity postFeed(Long userId, List<FeedImageEntity> images, String caption) {

        FeedEntity feedEntity = FeedEntity.builder()
                .userId(userId)
                .caption(caption)
                .images(images)
                .build();

        return feedRepository.save(feedEntity);
    }

    @Override
    public Page<FeedEntity> findAllByUserId(Long userId, Pageable pageable) {
        return feedRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public FeedEntity findById(Long feedId) {
        // todo: custom exception 처리
        return feedRepository.findById(feedId)
                .orElseThrow(RuntimeException::new);
    }
}
