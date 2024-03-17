package com.beomstagram.feed.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedImageEntity implements Serializable {

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "hashtags")
    private List<String> hashtags;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FeedImageEntity that = (FeedImageEntity) o;
        return Objects.equals(imageName, that.imageName) && Objects.equals(imageUrl, that.imageUrl)
                && Objects.equals(hashtags, that.hashtags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageName, imageUrl, hashtags);
    }
}
