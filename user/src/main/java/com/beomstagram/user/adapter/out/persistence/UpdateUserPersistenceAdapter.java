package com.beomstagram.user.adapter.out.persistence;

import com.beomstagram.common.annotation.PersistenceAdapter;
import com.beomstagram.user.application.port.out.UpdateUserPort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class UpdateUserPersistenceAdapter implements UpdateUserPort {

    private final UserRepository userRepository;

    @Override
    public UserEntity updateUserName(Long userId, String newName) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException());

        userEntity.updateName(newName);

        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity updateRefreshToken(Long userId, String token) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException());

        userEntity.updateRefreshToken(token);

        return userRepository.save(userEntity);
    }
}
