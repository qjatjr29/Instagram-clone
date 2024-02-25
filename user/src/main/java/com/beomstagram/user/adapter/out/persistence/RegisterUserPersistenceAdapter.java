package com.beomstagram.user.adapter.out.persistence;

import com.beomstagram.common.annotation.PersistenceAdapter;
import com.beomstagram.user.application.port.out.RegisterUserPort;

@PersistenceAdapter
public class RegisterUserPersistenceAdapter implements RegisterUserPort {

    private final UserRepository userRepository;

    public RegisterUserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUserEntity(String email, String name, String nickname, String phoneNumber, String password) {

        UserEntity user = UserEntity.builder()
                .email(email)
                .name(name)
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .password(password)
                .build();

        return userRepository.save(user);
    }
}
