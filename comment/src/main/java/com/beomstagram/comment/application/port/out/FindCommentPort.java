package com.beomstagram.comment.application.port.out;

import com.beomstagram.comment.adapter.out.persistance.CommentEntity;
import java.util.List;

public interface FindCommentPort {
    CommentEntity findById(Long commentId);

    List<CommentEntity> findAllByFeedId(Long feedId);
}
