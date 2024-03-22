package com.beomstagram.comment.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Reply {
    private Long replyId;
    private String username;
    private String content;
    private LocalDateTime createdAt;

    public static Reply generateReply(Long replyId, String username, String content, LocalDateTime createdAt) {
        return new Reply(replyId, username, content, createdAt);
    }
}
