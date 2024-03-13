package com.beomstagram.user.application.port.out;

import com.beomstagram.user.adapter.out.persistence.UserEntity;

public interface UpdateUserPort {
    UserEntity updateUserName(Long userId, String newName);
    UserEntity updateRefreshToken(Long userId, String token);
//    UserEntity updateUserNickName(Long userId, String newNickname);
//    UserEntity updateUserPassword(Long userId, String newPassword);
//    UserEntity updateUserProfileImage(Long userId, String newProfileImage);
}
