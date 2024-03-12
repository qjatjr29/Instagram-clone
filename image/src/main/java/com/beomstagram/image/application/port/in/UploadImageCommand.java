package com.beomstagram.image.application.port.in;

import com.beomstagram.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
public class UploadImageCommand extends SelfValidating<UploadImageCommand> {

    @NotNull
    private final MultipartFile imageFile;

    public UploadImageCommand(MultipartFile imageFile) {
        this.imageFile = imageFile;

        this.validateSelf();
    }

}
