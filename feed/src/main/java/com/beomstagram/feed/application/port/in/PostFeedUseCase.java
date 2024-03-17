package com.beomstagram.feed.application.port.in;

import com.beomstagram.feed.domain.Feed;

public interface PostFeedUseCase {
    Feed postFeed(PostFeedCommand command);
}
