package com.beomstagram.user.application.port.out;

import com.beomstagram.user.adapter.out.persistence.UserEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindUserPort {
    UserEntity findByUserId(Long userId);
    Page<UserEntity> findAllByNameOrNickName(String keyword, Pageable pageable);

    UserEntity findByEmail(String userEmail);

    Boolean isExistsUser(Long userId);

    List<UserEntity> findFollowingById(Long userId);

    List<UserEntity> findFollowersById(Long userId);
}
