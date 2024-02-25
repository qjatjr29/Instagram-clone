package com.beomstagram.user.application.port.in;

import com.beomstagram.user.adapter.in.web.FindUserResultDto;
import com.beomstagram.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindUserUseCase {
    User findByUserId(Long userId);
    Page<FindUserResultDto> findAllByNameOrNickName(Long userId, String keyword, Pageable pageable);
}
