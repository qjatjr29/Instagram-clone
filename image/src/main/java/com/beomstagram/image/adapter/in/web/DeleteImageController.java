package com.beomstagram.image.adapter.in.web;

import com.beomstagram.common.ApiResponse;
import com.beomstagram.common.ApiResponseMessage;
import com.beomstagram.image.application.port.in.DeleteImageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeleteImageController {

    private final DeleteImageUseCase deleteImageUseCase;

    @DeleteMapping("/image")
    public ApiResponse<Void> deleteImage(@RequestParam("imageName") String imageName) {

        deleteImageUseCase.deleteImage(imageName);
        return new ApiResponse<>(ApiResponseMessage.SUCCESS_REQUEST);
    }

    @DeleteMapping("/images")
    public ApiResponse<Void> deleteImages(@RequestBody DeleteImageRequest request) {
        deleteImageUseCase.deleteImages(request.getImageNames());
        return new ApiResponse<>(ApiResponseMessage.SUCCESS_REQUEST);
    }

}
