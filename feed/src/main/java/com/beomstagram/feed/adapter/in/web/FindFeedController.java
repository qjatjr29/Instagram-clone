package com.beomstagram.feed.adapter.in.web;

import com.beomstagram.common.ApiResponse;
import com.beomstagram.common.ApiResponseMessage;
import com.beomstagram.common.annotation.WebAdapter;
import com.beomstagram.feed.application.port.in.FindFeedUseCase;
import com.beomstagram.feed.domain.Feed;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/feed-service")
@RequiredArgsConstructor
public class FindFeedController {

    private final FindFeedUseCase findFeedUseCase;

    @GetMapping
    public ApiResponse<Page<Feed>> findAllByUserId(@RequestParam("user") Long userId,
                                                   @PageableDefault Pageable pageable) {

        Page<Feed> feeds = findFeedUseCase.findAllByUserId(userId, pageable);

        return new ApiResponse<>(ApiResponseMessage.SUCCESS_REQUEST, feeds);
    }

    // todo: userId를 통해서 해당 유저가 지금 로그인한 유저인지 아닌지 확인
    @GetMapping("/{feedId}")
    public ApiResponse<Feed> findByFeedId(@PathVariable("feedId") Long feedId,
                                          @Nullable @RequestHeader("userId") Long userId) {

        Feed feed = findFeedUseCase.findByFeedId(userId, feedId);
        return new ApiResponse<>(ApiResponseMessage.SUCCESS_REQUEST, feed);
    }

}
