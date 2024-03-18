package com.beomstagram.feed.application.port.in;

import com.beomstagram.feed.domain.Feed;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindFeedUseCase {
    Page<Feed> findAllByUserId(Long userId, Pageable pageable);

    Feed findByFeedId(@Nullable Long userId, Long feedId);
}
