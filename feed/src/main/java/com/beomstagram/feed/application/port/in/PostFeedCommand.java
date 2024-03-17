package com.beomstagram.feed.application.port.in;

import com.beomstagram.common.SelfValidating;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
public class PostFeedCommand extends SelfValidating<PostFeedCommand> {

    @NotNull
    private final Long userId;

    @NotNull
    private final List<MultipartFile> images;

    private final List<List<String>> hashtags;

    @Nullable
    private final String caption;

    public PostFeedCommand(Long userId, List<MultipartFile> images, List<List<String>> hashtags, @Nullable String caption) {
        this.userId = userId;
        this.images = images;
        this.hashtags = hashtags;
        this.caption = caption;

        this.validateSelf();
    }
}
