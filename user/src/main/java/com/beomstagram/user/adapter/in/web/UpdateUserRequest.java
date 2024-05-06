package com.beomstagram.user.adapter.in.web;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateUserRequest {
    @Nullable
    private String email;
    @Nullable
    private String name;
    @Nullable
    private String password;
    @Nullable
    private String profileImage;
}
