package com.beomstagram.user.application.service;

import com.beomstagram.common.annotation.UseCase;
import com.beomstagram.user.adapter.out.persistence.UserEntity;
import com.beomstagram.user.application.port.in.AuthUserUseCase;
import com.beomstagram.user.application.port.in.LoginUserCommand;
import com.beomstagram.user.application.port.out.AuthUserPort;
import com.beomstagram.user.application.port.out.FindUserPort;
import com.beomstagram.user.application.port.out.UpdateUserPort;
import com.beomstagram.user.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class AuthUserService implements AuthUserUseCase {

    private final AuthUserPort authUserPort;
    private final FindUserPort findUserPort;
    private final UpdateUserPort updateUserPort;
//    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtToken login(LoginUserCommand registerUserCommand) {

        // login 정보를 통해 인증
        UserEntity userEntity = findUserPort.findByEmail(registerUserCommand.getUserEmail());
        userEntity.checkPwd(registerUserCommand.getPassword());

        String jwtToken = authUserPort.generateJwtToken(userEntity.getId());
        String refreshToken = authUserPort.generateRefreshToken(userEntity.getId());

        updateUserPort.updateRefreshToken(userEntity.getId(), refreshToken);

        return JwtToken.generateJwtToken(userEntity.getId(), jwtToken, refreshToken);
    }
}
