package com.beomstagram.user.adapter.in.web;

import com.beomstagram.common.ApiResponse;
import com.beomstagram.common.ApiResponseMessage;
import com.beomstagram.common.annotation.WebAdapter;
import com.beomstagram.user.application.port.in.FollowCommand;
import com.beomstagram.user.application.port.in.FollowUseCase;
import com.beomstagram.user.domain.User;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class FollowUserController {

    private final FollowUseCase followUseCase;

    @PostMapping("/{userId}/follow/{followerId}")
    public ApiResponse<User> followUser(@PathVariable Long userId, @PathVariable Long followerId) {

        FollowCommand followCommand = FollowCommand.builder()
                .userId(userId)
                .followerId(followerId)
                .build();

        User user = followUseCase.follow(followCommand);

        return new ApiResponse(ApiResponseMessage.SUCCESS_REQUEST, user);
    }

    // todo :  GraphQL을 통해 원하는 데이터만 가져가도록 한다.
    @GetMapping("/{userId}/followers")
    public ApiResponse<Page<User>> getFollowersList(
            @PathVariable Long userId,
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<User> followersList = followUseCase.findFollowers(userId, pageable);

        return new ApiResponse(ApiResponseMessage.SUCCESS_REQUEST, followersList);
    }

    @GetMapping("/{userId}/following")
    public ApiResponse<Page<User>> getFollowingList(
            @PathVariable Long userId,
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<User> followingList = followUseCase.findFollowing(userId, pageable);

        return new ApiResponse(ApiResponseMessage.SUCCESS_REQUEST, followingList);
    }

}
