package com.beomstagram.feed.adapter.in.web;

import com.beomstagram.common.ApiResponse;
import com.beomstagram.common.ApiResponseMessage;
import com.beomstagram.common.annotation.WebAdapter;
import com.beomstagram.feed.application.port.in.PostFeedCommand;
import com.beomstagram.feed.application.port.in.PostFeedUseCase;
import com.beomstagram.feed.domain.Feed;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@WebAdapter
@RestController
@RequestMapping("/feed-service")
@RequiredArgsConstructor
public class PostFeedController {

    private final PostFeedUseCase postFeedUseCase;

    @PostMapping("/post/{userId}")
    public ApiResponse<Feed> postFeed(@PathVariable("userId") Long userId,
                                  @RequestPart("feedRequest") FeedRequest feedRequest,
                                  @RequestPart("image") List<MultipartFile> image) {

        PostFeedCommand command = PostFeedCommand.builder()
                .userId(userId)
                .caption(feedRequest.getCaption())
                .hashtags(feedRequest.getHashtags())
                .images(image)
                .build();

        Feed feed = postFeedUseCase.postFeed(command);

        return new ApiResponse<>(ApiResponseMessage.SUCCESS_REQUEST, feed);
    }

}
