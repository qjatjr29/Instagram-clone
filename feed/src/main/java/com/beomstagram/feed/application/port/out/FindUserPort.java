package com.beomstagram.feed.application.port.out;

import com.beomstagram.feed.adapter.out.service.User;

public interface FindUserPort {
    User findUser(Long userId);

    Boolean isExistsUser(Long userId);
}
