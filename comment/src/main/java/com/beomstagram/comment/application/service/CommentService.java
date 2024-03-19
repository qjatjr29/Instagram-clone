package com.beomstagram.comment.application.service;

import com.beomstagram.comment.adapter.out.persistance.CommentEntity;
import com.beomstagram.comment.adapter.out.persistance.CommentMapper;
import com.beomstagram.comment.application.port.in.CommentCommand;
import com.beomstagram.comment.application.port.in.CommentUseCase;
import com.beomstagram.comment.application.port.out.CommentPort;
import com.beomstagram.comment.application.port.out.FindFeedPort;
import com.beomstagram.comment.domain.Comment;
import com.beomstagram.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class CommentService implements CommentUseCase {

    private final CommentPort commentPort;
    private final FindFeedPort findFeedPort;
    private final CommentMapper commentMapper;

    @Override
    public Comment commentFeed(CommentCommand command) {
        // 해당 Feed Id에 해당하는 피드가 존재하는지 확인
        if(!findFeedPort.isExistsFeed(command.getPostId())) {
            throw new RuntimeException();
        }

        // 존재 한다면, 댓글 로직 수행
        CommentEntity commentEntity = commentPort.commentInFeed(command.getPostId(),
                command.getUserId(),
                command.getUsername(),
                command.getContent());

        return commentMapper.mapToDomain(commentEntity);
    }
}
