package com.beomstagram.user.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateUserRequest {
    private String email;
    private String name;
    private String password;
}
