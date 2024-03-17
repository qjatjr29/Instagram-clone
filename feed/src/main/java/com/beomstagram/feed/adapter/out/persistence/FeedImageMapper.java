package com.beomstagram.feed.adapter.out.persistence;

import com.beomstagram.feed.domain.FeedImage;
import org.springframework.stereotype.Component;

@Component
public class FeedImageMapper {

    public FeedImage mapToDomain(FeedImageEntity feedImageEntity) {
        return FeedImage.generateFeedImage(
                feedImageEntity.getImageName(),
                feedImageEntity.getImageUrl(),
                feedImageEntity.getHashtags());
    }
}
