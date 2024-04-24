package com.beomstagram.comment.application.service;

import com.beomstagram.comment.adapter.out.persistance.CommentEntity;
import com.beomstagram.comment.adapter.out.persistance.CommentMapper;
import com.beomstagram.comment.adapter.out.service.feign.User;
import com.beomstagram.comment.application.port.in.CommentCommand;
import com.beomstagram.comment.application.port.in.CommentUseCase;
import com.beomstagram.comment.application.port.out.CommentPort;
import com.beomstagram.comment.application.port.out.FindFeedPort;
import com.beomstagram.comment.application.port.out.FindUserPort;
import com.beomstagram.comment.domain.Comment;
import com.beomstagram.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class CommentService implements CommentUseCase{

    private final CommentPort commentPort;
    private final FindFeedPort findFeedPort;
    private final FindUserPort findUserPort;
    private final CommentMapper commentMapper;

    @Override
    public Comment commentFeed(CommentCommand command) {

        if(!findFeedPort.isExistsFeed(command.getPostId())) {
            throw new NotFoundFeedException();
        }
        User writer = findUserPort.findByUserId(command.getUserId());

        CommentEntity commentEntity = commentPort.commentInFeed(command.getPostId(),
                command.getUserId(),
                writer.nickname(),
                writer.profileImage(),
                command.getContent());

        return commentMapper.mapToDomain(commentEntity);
    }

}
