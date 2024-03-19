package com.beomstagram.user.application.port.out;

public interface AuthUserPort {
    String generateJwtToken(Long id, String username);
    String generateRefreshToken(Long id);
}
