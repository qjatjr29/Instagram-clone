package com.beomstagram.image.application.service;

import com.beomstagram.image.application.port.in.DeleteImageUseCase;
import com.beomstagram.image.application.port.out.DeleteImagePort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteImageService implements DeleteImageUseCase {

    private final DeleteImagePort deleteImagePort;

    @Override
    public void deleteImage(String imageName) {
        deleteImagePort.deleteImage(imageName);
    }

    @Override
    public void deleteImages(List<String> imageNames) {
        deleteImagePort.deleteImages(imageNames);
    }
}
