package com.beomstagram.image.application.port.in;

import com.beomstagram.image.adapter.out.external.aws.UploadedImage;

public interface UploadImageUseCase {
    UploadedImage uploadImage(UploadImageCommand command);
}
