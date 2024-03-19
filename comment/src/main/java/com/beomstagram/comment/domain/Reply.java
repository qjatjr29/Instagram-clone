package com.beomstagram.comment.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Reply {
    private String username;
    private String reply;

    public static Reply generateReply(String username, String reply) {
        return new Reply(username, reply);
    }
}
