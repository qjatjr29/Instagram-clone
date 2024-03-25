package com.beomstagram.comment.application.port.out;

import com.beomstagram.comment.adapter.out.persistance.CommentEntity;

public interface CommentPort {
    CommentEntity commentInFeed(Long postId, Long userId, String content);
}
