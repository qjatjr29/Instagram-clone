package com.beomstagram.comment.adapter.out.service;

import com.beomstagram.comment.adapter.out.service.feign.User;
import com.beomstagram.comment.adapter.out.service.feign.UserFeignClient;
import com.beomstagram.comment.application.port.out.FindUserPort;
import com.beomstagram.common.annotation.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class UserServiceAdapter implements FindUserPort {

    private final UserFeignClient userFeignClient;

    @Override
    public User findByUserId(Long userId) {
        return userFeignClient.findByUserId(userId);
    }
}

