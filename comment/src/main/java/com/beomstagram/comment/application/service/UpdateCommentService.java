package com.beomstagram.comment.application.service;

import com.beomstagram.comment.adapter.out.persistance.CommentEntity;
import com.beomstagram.comment.adapter.out.persistance.CommentMapper;
import com.beomstagram.comment.application.port.in.DeleteCommentCommand;
import com.beomstagram.comment.application.port.in.UpdateCommentCommand;
import com.beomstagram.comment.application.port.in.UpdateCommentUseCase;
import com.beomstagram.comment.application.port.out.UpdateCommentPort;
import com.beomstagram.comment.application.port.out.UpdateReplyPort;
import com.beomstagram.comment.domain.Comment;
import com.beomstagram.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class UpdateCommentService implements UpdateCommentUseCase {

    private final UpdateCommentPort updateCommentPort;
    private final CommentMapper commentMapper;
    private final UpdateReplyPort updateReplyPort;

    @Override
    public Comment updateComment(UpdateCommentCommand command) {

        CommentEntity commentEntity = updateCommentPort
                .updateComment(command.getCommentId(), command.getUserId(), command.getContent());

        return commentMapper.mapToDomain(commentEntity);
    }

    @Override
    public void deleteComment(DeleteCommentCommand command) {
        updateCommentPort.deleteComment(command.getCommentId(), command.getUserId());
        updateReplyPort.deleteByCommentId(command.getCommentId());
    }
}
