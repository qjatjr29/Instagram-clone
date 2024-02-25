package com.beomstagram.user.adapter.out.persistence;

import com.beomstagram.common.annotation.PersistenceAdapter;
import com.beomstagram.user.application.port.out.FindUserPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@PersistenceAdapter
public class FindUserPersistenceAdapter implements FindUserPort {

    private final UserRepository userRepository;

    public FindUserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity findByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public Page<UserEntity> findAllByNameOrNickName(String keyword, Pageable pageable) {
        Page<UserEntity> users = userRepository
                .findAllByNameContainingOrNicknameContaining(keyword, keyword, pageable);

        return users;
    }
}
