package com.beomstagram.comment.application.service;

import com.beomstagram.comment.adapter.out.persistance.CommentEntity;
import com.beomstagram.comment.adapter.out.persistance.CommentMapper;
import com.beomstagram.comment.adapter.out.persistance.ReplyEntity;
import com.beomstagram.comment.application.port.in.ReplyCommand;
import com.beomstagram.comment.application.port.in.ReplyUseCase;
import com.beomstagram.comment.application.port.out.FindCommentPort;
import com.beomstagram.comment.application.port.out.ReplyPort;
import com.beomstagram.comment.application.port.out.UpdateCommentPort;
import com.beomstagram.comment.domain.Comment;
import com.beomstagram.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class ReplyService implements ReplyUseCase {

    private final ReplyPort replyPort;
    private final UpdateCommentPort updateCommentPort;
    private final FindCommentPort findCommentPort;
    private final CommentMapper commentMapper;

    @Override
    public Comment reply(ReplyCommand command) {

        CommentEntity commentEntity = findCommentPort.findById(command.getCommentId());
        ReplyEntity replyEntity = replyPort.reply(command.getUserId(), command.getUsername(), command.getContent());
        CommentEntity updatedCommentEntity = updateCommentPort.reply(commentEntity, replyEntity);

        return commentMapper.mapToDomain(updatedCommentEntity);
    }

}
