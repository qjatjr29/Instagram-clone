package com.beomstagram.comment.application.port.in;

import com.beomstagram.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeleteCommentCommand extends SelfValidating<DeleteCommentCommand> {

    @NotNull
    private Long commentId;

    @NotNull
    private Long userId;


    public DeleteCommentCommand(Long commentId, Long userId) {
        this.commentId = commentId;
        this.userId = userId;

        this.validateSelf();
    }
}
