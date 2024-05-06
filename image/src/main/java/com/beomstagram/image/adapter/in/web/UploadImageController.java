package com.beomstagram.image.adapter.in.web;

import com.beomstagram.image.adapter.out.external.aws.UploadedImage;
import com.beomstagram.image.application.port.in.UploadImageCommand;
import com.beomstagram.image.application.port.in.UploadImageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UploadImageController {

    private final UploadImageUseCase uploadImageUseCase;

    @PostMapping()
    public UploadedImage uploadImage(@RequestPart("image") MultipartFile imageFile) {

        UploadImageCommand command = UploadImageCommand
                .builder()
                .imageFile(imageFile)
                .build();

        return uploadImageUseCase.uploadImage(command);
    }

}
