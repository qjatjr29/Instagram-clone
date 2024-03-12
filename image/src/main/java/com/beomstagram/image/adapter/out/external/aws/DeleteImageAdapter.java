package com.beomstagram.image.adapter.out.external.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.beomstagram.common.annotation.ExternalSystemAdapter;
import com.beomstagram.image.application.port.out.DeleteImagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class DeleteImageAdapter implements DeleteImagePort {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public void deleteImage(String filename) {
        try {
            boolean isObjectExist = amazonS3.doesObjectExist(bucket, filename);
            if (isObjectExist) {
                amazonS3.deleteObject(bucket, filename);
            }
            throw new RuntimeException("없는 file 입니다.");
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }
}
