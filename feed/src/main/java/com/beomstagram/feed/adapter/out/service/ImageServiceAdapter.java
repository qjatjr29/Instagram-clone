package com.beomstagram.feed.adapter.out.service;

import com.beomstagram.common.annotation.ExternalSystemAdapter;
import com.beomstagram.feed.application.port.out.UploadImagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class ImageServiceAdapter implements UploadImagePort {

    private final ImageServiceClient imageServiceClient;

    @Override
    public UploadedS3Image uploadFeedImage(MultipartFile imageFile) {
        return imageServiceClient.uploadFeedImage(imageFile);
    }

}
