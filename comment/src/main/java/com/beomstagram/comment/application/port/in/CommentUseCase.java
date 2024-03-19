package com.beomstagram.comment.application.port.in;

import com.beomstagram.comment.domain.Comment;

public interface CommentUseCase {
    Comment commentFeed(CommentCommand command);
}
