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
public class DeleteImageCommand extends SelfValidating<DeleteImageCommand> {

    @NotNull
    private final String filename;

    @NotNull
    private final String fileUrl;

    public DeleteImageCommand(String filename, String fileUrl) {
        this.filename = filename;
        this.fileUrl = fileUrl;

        this.validateSelf();
    }
}
