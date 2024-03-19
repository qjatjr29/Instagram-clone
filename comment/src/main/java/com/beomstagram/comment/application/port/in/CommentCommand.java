package com.beomstagram.comment.application.port.in;

import com.beomstagram.common.SelfValidating;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentCommand extends SelfValidating<CommentCommand> {

    @NotNull
    private Long userId;

    @NotNull
    private Long postId;

    @NotBlank
    private String username;

    @Nullable
    private String content;

    public CommentCommand(Long userId, Long postId, String username, @Nullable String content) {
        this.userId = userId;
        this.postId = postId;
        this.username = username;
        this.content = content;

        this.validateSelf();
    }
}
