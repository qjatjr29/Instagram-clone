package com.beomstagram.image.application.service;


import com.beomstagram.image.adapter.out.external.aws.UploadedImage;
import com.beomstagram.image.application.port.in.UploadImageCommand;
import com.beomstagram.image.application.port.in.UploadImageUseCase;
import com.beomstagram.image.application.port.out.UploadImagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UploadImageService implements UploadImageUseCase {

    private final UploadImagePort uploadImagePort;

    @Override
    public UploadedImage uploadImage(UploadImageCommand command) {

        UploadedImage uploadedImage = uploadImagePort.uploadImage(command.getImageFile());

        return uploadedImage;
    }
}
