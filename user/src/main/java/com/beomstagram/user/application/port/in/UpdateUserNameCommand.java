package com.beomstagram.user.application.port.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
public class UpdateUserNameCommand {

    @NotBlank
    private final Long userId;

    @NotBlank
    private final String name;

}
