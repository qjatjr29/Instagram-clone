package com.beomstagram.comment.application.port.out;

import com.beomstagram.comment.adapter.out.persistance.ReplyEntity;

public interface ReplyPort {
    ReplyEntity reply(Long commentId, Long userId, String username, String profileImage, String content);

}
