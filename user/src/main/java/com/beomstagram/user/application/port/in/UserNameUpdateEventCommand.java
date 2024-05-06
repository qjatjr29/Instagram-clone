package com.beomstagram.user.application.port.in;

import com.beomstagram.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
public class UserNameUpdateEventCommand extends SelfValidating<UserNameUpdateEventCommand> {

    @NotBlank
    private final Long userId;

    @NotBlank
    private final String username;
}
