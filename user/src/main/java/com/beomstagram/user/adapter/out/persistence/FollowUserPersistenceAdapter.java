package com.beomstagram.user.adapter.out.persistence;

import com.beomstagram.common.annotation.PersistenceAdapter;
import com.beomstagram.user.application.port.out.FollowUserPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@PersistenceAdapter
public class FollowUserPersistenceAdapter implements FollowUserPort {

    private final UserRepository userRepository;

    public FollowUserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity follow(Long userId, Long followerId) {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException());

        UserEntity follower = userRepository.findById(followerId)
                .orElseThrow(() -> new IllegalArgumentException());

//        follower.getFollowers().add(user);
        user.getFollowing().add(follower);

        return userRepository.save(user);
    }

    @Override
    public Page<UserEntity> findFollowing(Long userId, Pageable pageable) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        return new PageImpl<>(user.getFollowing(), pageable, user.getFollowing().size());
    }

    @Override
    public int findCommonFollowersCount(Long userId, Long targetUserId) {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        UserEntity targetUser = userRepository.findById(targetUserId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        return userRepository.findCommonFollowersCount(userId, targetUserId);
    }

    @Override
    public Page<UserEntity> findFollowers(Long userId, Pageable pageable) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        return new PageImpl<>(user.getFollowers(), pageable, user.getFollowers().size());
    }
}
