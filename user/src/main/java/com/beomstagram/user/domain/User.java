package com.beomstagram.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class User {

    private final String email;
    private final String name;
    private final String nickname;
    private final String phoneNumber;
    private final String password;
    private final String profileImage;

    public static User generateUser(final String email, final String name, final String nickname,
                                    final String phoneNumber, final String password, final String profileImage) {
        return new User(email, name, nickname, phoneNumber, password, profileImage);
    }

}
