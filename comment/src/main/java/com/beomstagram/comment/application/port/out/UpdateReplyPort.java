package com.beomstagram.comment.application.port.out;

import com.beomstagram.comment.adapter.out.persistance.ReplyEntity;

public interface UpdateReplyPort {
    ReplyEntity updateReply(Long replyId, Long userId, String content);
    void deleteById(Long replyId, Long userId);
    void deleteByCommentId(Long commentId);
}
