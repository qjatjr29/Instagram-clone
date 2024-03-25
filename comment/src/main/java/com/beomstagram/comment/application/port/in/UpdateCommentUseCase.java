package com.beomstagram.comment.application.port.in;

import com.beomstagram.comment.domain.Comment;

public interface UpdateCommentUseCase {
    Comment updateComment(UpdateCommentCommand command);
}
