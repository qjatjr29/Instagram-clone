package com.beomstagram.image.application.service;

import com.beomstagram.image.application.port.in.DeleteImageUseCase;
import com.beomstagram.image.application.port.out.DeleteImagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteImageService implements DeleteImageUseCase {

    private final DeleteImagePort deleteImagePort;

    @Override
    public void deleteImage(String filename) {
        deleteImagePort.deleteImage(filename);
    }
}
