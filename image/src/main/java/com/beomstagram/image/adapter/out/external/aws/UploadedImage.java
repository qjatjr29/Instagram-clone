package com.beomstagram.image.adapter.out.external.aws;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UploadedImage {

    private String imageName;
    private String imageUrl;
}
