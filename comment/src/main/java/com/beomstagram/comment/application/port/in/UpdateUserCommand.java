package com.beomstagram.comment.application.port.in;

import com.beomstagram.common.SelfValidating;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateUserCommand extends SelfValidating<UpdateUserCommand> {

    @NotNull
    private Long userId;

    @Nullable
    private String username;

    @Nullable
    private String profileImage;

    public UpdateUserCommand(Long userId, @Nullable String username, @Nullable String profileImage) {
        this.userId = userId;
        this.username = username;
        this.profileImage = profileImage;
        this.validateSelf();
    }
}
