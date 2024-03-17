package com.beomstagram.feed.domain;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Feed {

    private final Long feedId;
    private final Long userId;
    private final String caption;
    private final List<FeedImage> feedImages;
    private final Long likeCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static Feed generateFeed(Long feedId, Long userId,
                                    String cation, List<FeedImage> feedImages, Long likeCount,
                                    LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Feed(feedId, userId, cation, feedImages, likeCount, createdAt, updatedAt);
    }

    // todo : tag 관련 정보 추가 (ex : tag 된 user의 id?, tag 된 위치 정보)
    // todo : 댓글정보?
    // todo : 좋아요 정보?

}
