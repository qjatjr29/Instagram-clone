package com.beomstagram.user.application.port.out;

import com.beomstagram.user.adapter.out.persistence.UserEntity;

public interface RegisterUserPort {
    UserEntity createUserEntity(String email, String name, String nickname, String phoneNumber, String password);
}
