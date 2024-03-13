package com.beomstagram.user.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginUserRequest {

    private String userEmail;
    private String password;

}
