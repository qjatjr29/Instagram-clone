package com.beomstagram.comment.application.port.out;

import com.beomstagram.comment.adapter.out.persistance.CommentEntity;
import com.beomstagram.comment.adapter.out.persistance.ReplyEntity;

public interface UpdateCommentPort {
    void reply(Long commentId, ReplyEntity replyEntity);
    CommentEntity updateContent(Long commentId, Long userId, String content);
}
