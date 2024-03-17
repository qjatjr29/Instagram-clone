package com.beomstagram.feed.domain;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FeedImage {

    private final String imageName;
    private final String imageUrl;
    private final List<String> hashtags;

    public static FeedImage generateFeedImage(String imageName, String imageUrl, List<String> hashtags) {
        return new FeedImage(imageName, imageUrl, hashtags);
    }

}
