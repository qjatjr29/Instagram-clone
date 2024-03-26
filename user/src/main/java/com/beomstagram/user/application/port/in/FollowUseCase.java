package com.beomstagram.user.application.port.in;

import com.beomstagram.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FollowUseCase {
    User follow(FollowCommand followCommand);
}
