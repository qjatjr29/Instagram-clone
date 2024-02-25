package com.beomstagram.user.application.port.in;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
public class FollowCommand {

    @NotNull
    private final Long userId;

    @NotNull
    private final Long followerId;

}
