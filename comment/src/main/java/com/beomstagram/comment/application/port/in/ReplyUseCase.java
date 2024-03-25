package com.beomstagram.comment.application.port.in;

import com.beomstagram.comment.domain.Reply;

public interface ReplyUseCase {
    Reply reply(ReplyCommand command);

    Reply updateReply(UpdateReplyCommand command);
}
