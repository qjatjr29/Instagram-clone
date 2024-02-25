package com.beomstagram.user.application.service;

import com.beomstagram.common.annotation.UseCase;
import com.beomstagram.user.adapter.out.persistence.UserEntity;
import com.beomstagram.user.adapter.out.persistence.UserMapper;
import com.beomstagram.user.application.port.in.FollowCommand;
import com.beomstagram.user.application.port.in.FollowUseCase;
import com.beomstagram.user.application.port.out.FollowUserPort;
import com.beomstagram.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@UseCase
public class FollowUserService implements FollowUseCase {

    private final FollowUserPort followUserPort;
    private final UserMapper userMapper;

    public FollowUserService(FollowUserPort followUserPort, UserMapper userMapper) {
        this.followUserPort = followUserPort;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public User follow(FollowCommand followCommand) {

        UserEntity userEntity = followUserPort.follow(followCommand.getUserId(), followCommand.getFollowerId());

        return userMapper.mapToDomain(userEntity);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<User> findFollowing(Long userId, Pageable pageable) {
        return followUserPort.findFollowing(userId, pageable)
                .map(userMapper::mapToDomain);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<User> findFollowers(Long userId, Pageable pageable) {
        return followUserPort.findFollowers(userId, pageable)
                .map(userMapper::mapToDomain);
    }
}
