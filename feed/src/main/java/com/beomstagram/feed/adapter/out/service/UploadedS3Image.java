package com.beomstagram.feed.adapter.out.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UploadedS3Image {
    private String imageUrl;
    private String imageName;
}
