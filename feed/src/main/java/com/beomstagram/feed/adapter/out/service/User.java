package com.beomstagram.feed.adapter.out.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {
    private Long userId;
    private String email;
    private String name;
    private String nickname;
    private String phoneNumber;
    private String password;
    private String profileImage;
}
