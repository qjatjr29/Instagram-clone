package com.beomstagram.comment.adapter.out.persistance;

import com.beomstagram.comment.domain.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    private final ReplyMapper replyMapper;

    public CommentMapper(ReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }

    public Comment mapToDomain(CommentEntity commentEntity) {
        return Comment.generateComment(
                commentEntity.getId(),
                commentEntity.getPostId(),
                commentEntity.getUserId(),
                commentEntity.getUsername(),
                commentEntity.getContent(),
                commentEntity.getReplies().stream()
                        .map(replyMapper::mapToDomain)
                        .toList(),
                commentEntity.getPostType(),
                commentEntity.getCreatedAt(),
                commentEntity.getUpdatedAt());
    }
}
