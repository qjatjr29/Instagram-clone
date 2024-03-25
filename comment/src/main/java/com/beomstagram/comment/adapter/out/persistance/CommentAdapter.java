package com.beomstagram.comment.adapter.out.persistance;

import com.beomstagram.comment.application.port.out.CommentPort;
import com.beomstagram.comment.application.port.out.UpdateCommentPort;
import com.beomstagram.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CommentAdapter implements CommentPort, UpdateCommentPort {

    private final CommentRepository commentRepository;

    @Override
    public CommentEntity commentInFeed(Long postId, Long userId, String content) {

        CommentEntity commentEntity = CommentEntity.builder()
                .postId(postId)
                .userId(userId)
                .content(content)
                .postType(PostType.FEED)
                .build();

        return commentRepository.save(commentEntity);
    }


    @Override
    public void reply(Long commentId, ReplyEntity replyEntity) {

        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException());

        commentEntity.addReply(replyEntity);
        commentRepository.save(commentEntity);
    }

    @Override
    public CommentEntity updateContent(Long commentId, Long userId, String content) {

        CommentEntity commentEntity = commentRepository.findByIdAndUserId(commentId, userId)
                .orElseThrow(() -> new IllegalArgumentException());

        commentEntity.updateContent(content);
        return commentRepository.save(commentEntity);
    }

}
