package com.beomstagram.feed.adapter.out.service;

import com.beomstagram.common.annotation.ExternalSystemAdapter;
import com.beomstagram.feed.application.port.out.FindUserPort;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class UserServiceAdapter implements FindUserPort {

    private final UserClient userClient;

    @Override
    public User findUser(Long userId) {
        return userClient.findUser(userId);
    }

    @Override
    public Boolean isExistsUser(Long userId) {
        return userClient.isExistsUser(userId);
    }
}
