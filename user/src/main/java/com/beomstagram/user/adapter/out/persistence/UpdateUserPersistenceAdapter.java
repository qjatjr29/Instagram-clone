package com.beomstagram.user.adapter.out.persistence;

import com.beomstagram.common.annotation.PersistenceAdapter;
import com.beomstagram.user.application.port.out.RegisterUserPort;
import com.beomstagram.user.application.port.out.UpdateUserPort;

@PersistenceAdapter
public class UpdateUserPersistenceAdapter implements UpdateUserPort {

    private final UserRepository userRepository;

    public UpdateUserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity updateUserName(Long userId, String newName) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException());

        userEntity.updateName(newName);

        return userRepository.save(userEntity);
    }
}
