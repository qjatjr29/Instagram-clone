package com.beomstagram.comment.adapter.out.persistance;

import com.beomstagram.comment.domain.Comment;
import com.beomstagram.comment.domain.Reply;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment mapToDomain(CommentEntity commentEntity) {
        return Comment.generateComment(
                commentEntity.getUserId(),
                commentEntity.getUsername(),
                commentEntity.getContent(),
                commentEntity.getReplies()
                        .stream()
                        .map(this::mapToReplyDomain)
                        .toList(),
                commentEntity.getCreatedAt(),
                commentEntity.getUpdatedAt());
    }

    private Reply mapToReplyDomain(ReplyEntity replyEntity) {
        return Reply.generateReply(replyEntity.getUsername(), replyEntity.getReply());
    }
}
