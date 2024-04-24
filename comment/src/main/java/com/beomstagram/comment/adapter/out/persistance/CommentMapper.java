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
        return Comment.builder()
                .commentId(commentEntity.getId())
                .userId(commentEntity.getUserId())
//                .postId(commentEntity.getPostId())
                .username(commentEntity.getUsername())
                .profileImage(commentEntity.getProfileImage())
                .content(commentEntity.getContent())
                .replyList(commentEntity.getReplyList()
                        .stream()
                        .map(replyMapper::mapToDomain)
                        .toList())
                .replyCount(commentEntity.getReplyCount())
//                .postType(commentEntity.getPostType())
                .createdAt(commentEntity.getCreatedAt())
                .build();
    }
}
