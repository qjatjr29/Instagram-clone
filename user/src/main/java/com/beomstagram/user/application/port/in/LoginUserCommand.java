package com.beomstagram.user.application.port.in;

import com.beomstagram.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
public class LoginUserCommand extends SelfValidating<LoginUserCommand> {

    @NotBlank
    private final String userEmail;

    @NotBlank
    private final String password;

    public LoginUserCommand(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;

        this.validateSelf();
    }
}
