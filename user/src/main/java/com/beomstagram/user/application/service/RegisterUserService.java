package com.beomstagram.user.application.service;

import com.beomstagram.common.annotation.UseCase;
import com.beomstagram.user.adapter.out.persistence.UserEntity;
import com.beomstagram.user.adapter.out.persistence.UserMapper;
import com.beomstagram.user.application.port.in.RegisterUserCommand;
import com.beomstagram.user.application.port.in.RegisterUserUseCase;
import com.beomstagram.user.application.port.out.RegisterUserPort;
import com.beomstagram.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterUserService implements RegisterUserUseCase {

    private final RegisterUserPort registerUserPort;
    private final UserMapper userMapper;

    @Override
    public User registerUser(RegisterUserCommand registerUserCommand) {
        UserEntity userEntity = registerUserPort.createUserEntity(registerUserCommand.getEmail(),
                registerUserCommand.getName(),
                registerUserCommand.getNickname(),
                registerUserCommand.getPhoneNumber(),
                registerUserCommand.getPassword());

        return userMapper.mapToDomain(userEntity);
    }
}
