package com.beomstagram.image.adapter.in.web;

import com.beomstagram.common.ApiResponse;
import com.beomstagram.common.ApiResponseMessage;
import com.beomstagram.image.application.port.in.DeleteImageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/image-service")
@RequiredArgsConstructor
public class DeleteImageController {

    private final DeleteImageUseCase deleteImageUseCase;

    @DeleteMapping("/image")
    public ApiResponse<Void> deleteImage(@RequestParam("filename") String filename) {

        deleteImageUseCase.deleteImage(filename);
        return new ApiResponse<>(ApiResponseMessage.SUCCESS_REQUEST);
    }

}
