package com.beomstagram.feed.application.port.out;

import com.beomstagram.feed.adapter.out.persistence.FeedEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindFeedPort {
    Page<FeedEntity> findAllByUserId(Long userId, Pageable pageable);

    FeedEntity findById(Long feedId);

    Boolean isExitsFeed(Long feedId);
}
