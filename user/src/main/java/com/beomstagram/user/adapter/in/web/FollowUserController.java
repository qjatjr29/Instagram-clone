package com.beomstagram.user.adapter.in.web;

import com.beomstagram.common.ApiResponse;
import com.beomstagram.common.ApiResponseMessage;
import com.beomstagram.common.annotation.WebAdapter;
import com.beomstagram.user.application.port.in.FollowCommand;
import com.beomstagram.user.application.port.in.FollowUseCase;
import com.beomstagram.user.domain.User;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class FollowUserController {

    private final FollowUseCase followUseCase;

    @PostMapping("/follow/{followerId}")
    public ApiResponse<User> followUser(@RequestHeader("userId") Long userId, @PathVariable Long followerId) {

        FollowCommand followCommand = FollowCommand.builder()
                .userId(userId)
                .followerId(followerId)
                .build();

        User user = followUseCase.follow(followCommand);

        return new ApiResponse(ApiResponseMessage.SUCCESS_REQUEST, user);
    }

}
