package com.beomstagram.feed.adapter.out.persistence;

import com.beomstagram.feed.domain.Feed;
import com.beomstagram.feed.domain.FeedImage;
import org.springframework.stereotype.Component;

@Component
public class FeedMapper {

    public Feed mapToDomain(FeedEntity feedEntity) {
        return Feed.generateFeed(
                feedEntity.getFeedId(),
                feedEntity.getUserId(),
                feedEntity.getCaption(),
                feedEntity.getImages()
                        .stream()
                        .map(this::mapToImage)
                        .toList(),
                feedEntity.getLikeCount(),
                feedEntity.getCreatedAt(),
                feedEntity.getUpdatedAt());
    }

    private FeedImage mapToImage(FeedImageEntity feedImageEntity) {
        return FeedImage.generateFeedImage(
                feedImageEntity.getImageName(),
                feedImageEntity.getImageUrl(),
                feedImageEntity.getHashtags());
    }
}
