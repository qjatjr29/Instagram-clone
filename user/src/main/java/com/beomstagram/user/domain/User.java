package com.beomstagram.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class User {

    private final Long userId;
    private final String email;
    private final String name;
    private final String nickname;
    private final String phoneNumber;
    private final String profileImage;
    private final String refreshToken;
    // todo : 공개, 비공개
    // private final Boolean isPrivate;

}
