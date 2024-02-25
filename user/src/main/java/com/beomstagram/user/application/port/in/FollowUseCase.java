package com.beomstagram.user.application.port.in;

import com.beomstagram.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FollowUseCase {
    User follow(FollowCommand followCommand);
    Page<User> findFollowing(Long userId, Pageable pageable);
    Page<User> findFollowers(Long userId, Pageable pageable);
}
