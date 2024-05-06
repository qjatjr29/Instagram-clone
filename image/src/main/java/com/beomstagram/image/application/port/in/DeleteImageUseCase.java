package com.beomstagram.image.application.port.in;


import java.util.List;

public interface DeleteImageUseCase {
    void deleteImage(String imageName);

    void deleteImages(List<String> imageNames);
}
