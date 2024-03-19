package com.beomstagram.comment.adapter.out.persistance;

import com.beomstagram.comment.application.port.out.CommentPort;
import com.beomstagram.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CommentAdapter implements CommentPort {

    private final CommentRepository commentRepository;

    @Override
    public CommentEntity commentInFeed(Long postId, Long userId, String username, String content) {

        CommentEntity commentEntity = CommentEntity.builder()
                .postId(postId)
                .userId(userId)
                .username(username)
                .content(content)
                .postType(PostType.FEED)
                .build();

        return commentRepository.save(commentEntity);
    }
}
