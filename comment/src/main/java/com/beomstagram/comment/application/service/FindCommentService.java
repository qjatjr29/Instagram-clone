package com.beomstagram.comment.application.service;

import com.beomstagram.comment.adapter.out.persistance.CommentEntity;
import com.beomstagram.comment.adapter.out.persistance.CommentMapper;
import com.beomstagram.comment.application.port.in.FindCommentUseCase;
import com.beomstagram.comment.application.port.out.FindCommentPort;
import com.beomstagram.comment.application.port.out.FindFeedPort;
import com.beomstagram.comment.domain.Comment;
import com.beomstagram.common.annotation.UseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindCommentService implements FindCommentUseCase {

    private final FindFeedPort findFeedPort;
    private final FindCommentPort findCommentPort;
    private final CommentMapper commentMapper;

    @Override
    public Comment findById(Long commentId) {
        CommentEntity commentEntity = findCommentPort.findById(commentId);
        return commentMapper.mapToDomain(commentEntity);
    }

    @Override
    public Page<Comment> findAllByFeedId(Long feedId, Pageable pageable) {
        // 해당 feed 가 존재하는지 확인
        if(!findFeedPort.isExistsFeed(feedId)) {
            throw new IllegalArgumentException();
        }

        List<CommentEntity> commentEntityList = findCommentPort.findAllByFeedId(feedId);

        List<Comment> comments = commentEntityList.stream()
                .map(commentMapper::mapToDomain)
                .toList();

        return new PageImpl<>(comments, pageable, commentEntityList.size());
    }
}
