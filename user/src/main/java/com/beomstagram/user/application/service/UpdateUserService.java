package com.beomstagram.user.application.service;

import com.beomstagram.common.annotation.UseCase;
import com.beomstagram.user.adapter.out.persistence.UserEntity;
import com.beomstagram.user.adapter.out.persistence.UserMapper;
import com.beomstagram.user.application.port.in.UpdateUserNameCommand;
import com.beomstagram.user.application.port.in.UpdateUserUseCase;
import com.beomstagram.user.application.port.out.UpdateUserPort;
import com.beomstagram.user.domain.User;
import org.springframework.transaction.annotation.Transactional;

@UseCase
//@Transactional
public class UpdateUserService implements UpdateUserUseCase {

    private final UpdateUserPort updateUserPort;
    private final UserMapper userMapper;

    public UpdateUserService(UpdateUserPort updateUserPort, UserMapper userMapper) {
        this.updateUserPort = updateUserPort;
        this.userMapper = userMapper;
    }

    @Override
    public User updateUserName(UpdateUserNameCommand updateUserNameCommand) {
        UserEntity userEntity = updateUserPort.updateUserName(
                updateUserNameCommand.getUserId(),
                updateUserNameCommand.getName());

        return userMapper.mapToDomain(userEntity);
    }
}
