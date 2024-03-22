package com.beomstagram.comment.domain;

import com.beomstagram.comment.adapter.out.persistance.PostType;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Comment {

    private Long commentId;
    private Long postId;
    private Long userId;
    private String username;
    private String content;
    private List<Reply> replyList;
    private PostType postType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Comment generateComment(Long commentId, Long postId, Long userId, String username, String content,
                                          List<Reply> replyList, PostType postType,
                                          LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Comment(commentId, postId, userId, username, content, replyList, postType, createdAt, updatedAt);
    }
}
