package com.beomstagram.user.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterUserRequest {
    private String email;
    private String name;
    private String nickname;
    private String phoneNumber;
    private String password;
}
