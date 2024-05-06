package com.beomstagram.image.application.port.out;

import java.util.List;

public interface DeleteImagePort {
    void deleteImage(String imageName);
    void deleteImages(List<String> imageNames);
}
