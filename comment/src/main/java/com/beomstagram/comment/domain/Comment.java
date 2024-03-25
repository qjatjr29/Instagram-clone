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

    private final Long commentId;
    private final Long postId;
    private final Long userId;
    private final String content;
    private final List<Reply> replyList;
    private final Long replyCount;
    private final PostType postType;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

}
