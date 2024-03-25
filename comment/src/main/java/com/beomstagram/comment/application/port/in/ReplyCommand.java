package com.beomstagram.comment.application.port.in;

import com.beomstagram.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReplyCommand extends SelfValidating<ReplyCommand> {

    @NotNull
    private Long commentId;

    @NotNull
    private Long userId;

    @NotBlank
    private String content;

    public ReplyCommand(Long commentId, Long userId, String content) {
        this.commentId = commentId;
        this.userId = userId;
        this.content = content;

        this.validateSelf();
    }
}
