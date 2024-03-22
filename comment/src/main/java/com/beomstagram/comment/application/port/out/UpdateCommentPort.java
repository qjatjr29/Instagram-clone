package com.beomstagram.comment.application.port.out;

import com.beomstagram.comment.adapter.out.persistance.CommentEntity;
import com.beomstagram.comment.adapter.out.persistance.ReplyEntity;

public interface UpdateCommentPort {
    CommentEntity reply(CommentEntity commentEntity, ReplyEntity replyEntity);
}
