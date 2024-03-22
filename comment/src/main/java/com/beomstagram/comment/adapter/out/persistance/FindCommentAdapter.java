package com.beomstagram.comment.adapter.out.persistance;

import com.beomstagram.comment.application.port.out.FindCommentPort;
import com.beomstagram.common.annotation.PersistenceAdapter;
import java.util.List;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class FindCommentAdapter implements FindCommentPort {

    private final CommentRepository commentRepository;

    @Override
    public CommentEntity findById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public List<CommentEntity> findAllByFeedId(Long feedId) {

        return commentRepository.findAllByFeedId(feedId);
    }
}
