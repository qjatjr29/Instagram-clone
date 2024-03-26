package com.beomstagram.user.adapter.out.persistence;

import com.beomstagram.common.annotation.PersistenceAdapter;
import com.beomstagram.user.application.port.out.FindUserPort;
import java.util.List;
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
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Page<UserEntity> findAllByNameOrNickName(String keyword, Pageable pageable) {
        Page<UserEntity> users = userRepository
                .findAllByNameContainingOrNicknameContaining(keyword, keyword, pageable);

        return users;
    }

    @Override
    public UserEntity findByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }

    @Override
    public Boolean isExistsUser(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public List<UserEntity> findFollowingById(Long userId) {
        return userRepository.findFollowingByUserId(userId);
    }

    @Override
    public List<UserEntity> findFollowersById(Long userId) {
        return userRepository.findFollowersByUserId(userId);
    }
}
