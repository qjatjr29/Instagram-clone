package com.beomstagram.image.application.port.out;

import com.beomstagram.image.adapter.out.external.aws.UploadedImage;
import org.springframework.web.multipart.MultipartFile;

public interface UploadImagePort {
    UploadedImage uploadImage(final MultipartFile image);
}
