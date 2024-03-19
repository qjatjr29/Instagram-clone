package com.beomstagram.comment.adapter.out.service;

import com.beomstagram.comment.adapter.out.service.feign.FeedFeignClient;
import com.beomstagram.comment.application.port.out.FindFeedPort;
import com.beomstagram.common.annotation.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class FeedServiceAdapter implements FindFeedPort {

    private final FeedFeignClient feedFeignClient;

    @Override
    public boolean isExistsFeed(Long feedId) {
        return feedFeignClient.isExistsFeed(feedId);
    }
}
