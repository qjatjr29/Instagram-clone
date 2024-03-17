package com.beomstagram.feed.adapter.out.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "image-service")
public interface ImageServiceClient {

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    UploadedS3Image uploadFeedImage(@RequestPart(value = "image") MultipartFile imageFile);

    @DeleteMapping("/image")
    void delete(@RequestParam("imageName") String imageName);
}
