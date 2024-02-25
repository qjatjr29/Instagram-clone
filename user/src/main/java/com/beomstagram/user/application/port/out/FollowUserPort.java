package com.beomstagram.user.application.port.out;

import com.beomstagram.user.adapter.out.persistence.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FollowUserPort {
    UserEntity follow(Long userId, Long followerId);
    Page<UserEntity> findFollowers(Long userId, Pageable pageable);
    Page<UserEntity> findFollowing(Long userId, Pageable pageable);
    int findCommonFollowersCount(Long userId, Long targetUserId);
}
