package com.beomstagram.feed.application.port.out;

import com.beomstagram.feed.adapter.out.service.UploadedS3Image;
import org.springframework.web.multipart.MultipartFile;

public interface UploadImagePort {
    UploadedS3Image uploadFeedImage(MultipartFile imageFile);
//    FeedImageEntity uploadFeedImage(MultipartFile imageFile);
}
