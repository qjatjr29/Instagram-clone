package com.beomstagram.feed.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<FeedEntity, Long> {
    Page<FeedEntity> findAllByUserId(Long userId, Pageable pageable);
}
