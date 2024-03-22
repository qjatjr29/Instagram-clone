package com.beomstagram.comment.application.port.in;

import com.beomstagram.comment.domain.Comment;
import com.beomstagram.comment.domain.Reply;

public interface ReplyUseCase {
    Comment reply(ReplyCommand command);

//    Reply updateReply(UpdateReplyCommand command);
}
