package com.beomstagram.feed.application.port.out;

import com.beomstagram.feed.adapter.out.persistence.FeedEntity;
import com.beomstagram.feed.adapter.out.persistence.FeedImageEntity;
import java.util.List;

public interface PostFeedPort {
    FeedEntity postFeed(Long userId, List<FeedImageEntity> images, String caption);
}
