package com.beomstagram.user.application.port.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
public class RegisterUserCommand {

    @NotBlank
    private final String email;

    @NotBlank
    private final String name;

    private final String nickname;

    @NotBlank
    private final String phoneNumber;

    // todo: password - 특수문자, 대소문자, 숫자
    @NotBlank
    private final String password;

}
