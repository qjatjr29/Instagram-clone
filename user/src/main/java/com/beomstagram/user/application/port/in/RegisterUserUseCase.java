package com.beomstagram.user.application.port.in;

import com.beomstagram.user.domain.User;

public interface RegisterUserUseCase {
    User registerUser(RegisterUserCommand registerUserCommand);
}
