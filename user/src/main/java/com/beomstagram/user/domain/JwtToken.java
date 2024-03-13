package com.beomstagram.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class JwtToken {

    private final Long userId;
    private final String jwtToken;
    private final String refreshToken;

    public static JwtToken generateJwtToken(final Long userId, final String jwtToken, final String refreshToken) {
        return new JwtToken(userId, jwtToken, refreshToken);
    }

}
