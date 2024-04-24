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
    private Long userId;
    private String username;
    private String profileImage;
    private String content;
    private LocalDateTime createdAt;

    public static Reply generateReply(Long replyId, Long userId, String username, String profileImage,
                                      String content, LocalDateTime createdAt) {
        return new Reply(replyId, userId, username, profileImage, content, createdAt);
    }
}
