package com.beomstagram.comment.application.port.in;

import com.beomstagram.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeleteReplyCommand extends SelfValidating<DeleteReplyCommand> {

    @NotNull
    private Long replyId;

    @NotNull
    private Long userId;

    public DeleteReplyCommand(Long replyId, Long userId) {
        this.replyId = replyId;
        this.userId = userId;

        this.validateSelf();
    }
}
