package com.beomstagram.comment.application.port.out;

import com.beomstagram.comment.adapter.out.service.feign.User;

public interface FindUserPort {

    User findByUserId(Long userId);
}
