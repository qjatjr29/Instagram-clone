package com.beomstagram.comment.application.port.in;

import com.beomstagram.comment.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindCommentUseCase {
    Comment findById(Long commentId);

    Page<Comment> findAllByFeedId(Long feedId, Pageable pageable);
}
