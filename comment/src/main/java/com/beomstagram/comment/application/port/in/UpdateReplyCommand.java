package com.beomstagram.comment.application.port.in;

import com.beomstagram.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateReplyCommand extends SelfValidating<UpdateReplyCommand> {

    @NotNull
    private Long replyId;

    @NotNull
    private Long userId;

    @NotBlank
    private String content;

    public UpdateReplyCommand(Long replyId, Long userId, String content) {
        this.replyId = replyId;
        this.userId = userId;
        this.content = content;

        this.validateSelf();
    }
}
