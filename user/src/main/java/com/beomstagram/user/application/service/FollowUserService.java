package com.beomstagram.user.application.service;

import com.beomstagram.common.annotation.UseCase;
import com.beomstagram.user.adapter.out.persistence.UserEntity;
import com.beomstagram.user.adapter.out.persistence.UserMapper;
import com.beomstagram.user.application.port.in.FollowCommand;
import com.beomstagram.user.application.port.in.FollowUseCase;
import com.beomstagram.user.application.port.out.FollowUserPort;
import com.beomstagram.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@UseCase
@RequiredArgsConstructor
public class FollowUserService implements FollowUseCase {

    private final FollowUserPort followUserPort;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public User follow(FollowCommand followCommand) {

        UserEntity userEntity = followUserPort.follow(followCommand.getUserId(), followCommand.getFollowerId());

        return userMapper.mapToDomain(userEntity);
    }
}
