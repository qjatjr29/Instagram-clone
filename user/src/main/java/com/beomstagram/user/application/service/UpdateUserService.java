package com.beomstagram.user.application.service;

import com.beomstagram.common.annotation.UseCase;
import com.beomstagram.user.adapter.out.persistence.UserEntity;
import com.beomstagram.user.adapter.out.persistence.UserMapper;
import com.beomstagram.user.application.port.in.UpdateUserNameCommand;
import com.beomstagram.user.application.port.in.UpdateUserUseCase;
import com.beomstagram.user.application.port.in.UserNameUpdateEventCommand;
import com.beomstagram.user.application.port.out.UpdateUserPort;
import com.beomstagram.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class UpdateUserService implements UpdateUserUseCase {

    private final UpdateUserPort updateUserPort;
    private final UserMapper userMapper;
    private final UserUpdateEventService updateEventService;

    @Override
    public User updateUserName(UpdateUserNameCommand updateUserNameCommand) {
        UserEntity userEntity = updateUserPort.updateUserName(
                updateUserNameCommand.getUserId(),
                updateUserNameCommand.getName());

        updateEventService.usernameUpdateEventPublish(UserNameUpdateEventCommand.builder()
                .userId(updateUserNameCommand.getUserId())
                .username(updateUserNameCommand.getName())
                .build());

        return userMapper.mapToDomain(userEntity);
    }
}
