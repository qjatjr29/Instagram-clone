package com.beomstagram.comment.domain;

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

    private Long userId;
    private String username;
    private String content;
    private List<Reply> replyList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Comment generateComment(Long userId, String username, String content, List<Reply> replyList,
                                          LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Comment(userId, username, content, replyList, createdAt, updatedAt);
    }
}
