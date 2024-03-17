package com.beomstagram.feed.adapter.out.event;

import com.beomstagram.feed.adapter.out.service.UploadedS3Image;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UploadImageRollbackEvent {

    private final List<String> filenames;

    public static UploadImageRollbackEvent rollbackFeedImagesEvent(List<UploadedS3Image> feedImages) {

        List<String> filenames = feedImages.stream()
                .map(UploadedS3Image::getImageName)
                .toList();
        return new UploadImageRollbackEvent(filenames);
    }

}
