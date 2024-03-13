package com.beomstagram.user.adapter.out.persistence;

import com.beomstagram.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToDomain(UserEntity userEntity) {
        return User.builder()
                .userId(userEntity.getId())
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .nickname(userEntity.getNickname())
                .phoneNumber(userEntity.getPhoneNumber())
                .refreshToken(userEntity.getRefreshToken())
                .profileImage(userEntity.getProfileImage())
                .build();

    }

}
