package com.beomstagram.user.adapter.in.web;

import com.beomstagram.common.ApiResponse;
import com.beomstagram.common.ApiResponseMessage;
import com.beomstagram.common.annotation.WebAdapter;
import com.beomstagram.user.application.port.in.UpdateUserNameCommand;
import com.beomstagram.user.application.port.in.UpdateUserUseCase;
import com.beomstagram.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class UpdateUserController {

    private final UpdateUserUseCase updateUserUseCase;

    @PutMapping("/{userId}/name")
    public ApiResponse<User> updateUserName(@PathVariable Long userId, @RequestBody UpdateUserRequest updateUserRequest) {

        UpdateUserNameCommand command = UpdateUserNameCommand.builder()
                .userId(userId)
                .name(updateUserRequest.getName())
                .build();

        User user = updateUserUseCase.updateUserName(command);

        return new ApiResponse<>(ApiResponseMessage.SUCCESS_REQUEST, user);
    }

}
