package com.beomstagram.user.application.port.in;

import com.beomstagram.user.domain.JwtToken;

public interface AuthUserUseCase {
    JwtToken login(LoginUserCommand registerUserCommand);
}
