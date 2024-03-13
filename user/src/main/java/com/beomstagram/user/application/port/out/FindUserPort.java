package com.beomstagram.user.application.port.out;

import com.beomstagram.user.adapter.out.persistence.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindUserPort {
    UserEntity findByUserId(Long userId);
    Page<UserEntity> findAllByNameOrNickName(String keyword, Pageable pageable);

    UserEntity findByEmail(String userEmail);

    Boolean isExistsUser(Long userId);
}
