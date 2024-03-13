package com.beomstagram.user.adapter.in.web;

import com.beomstagram.common.ApiResponse;
import com.beomstagram.common.ApiResponseMessage;
import com.beomstagram.common.annotation.WebAdapter;
import com.beomstagram.user.application.port.in.RegisterUserCommand;
import com.beomstagram.user.application.port.in.RegisterUserUseCase;
import com.beomstagram.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class RegisterUserController {

    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping()
    public ApiResponse<User> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {

        RegisterUserCommand command = RegisterUserCommand.builder()
                .email(registerUserRequest.getEmail())
                .name(registerUserRequest.getName())
                .nickname(registerUserRequest.getNickname())
                .phoneNumber(registerUserRequest.getPhoneNumber())
                .password(registerUserRequest.getPassword())
                .build();

        User user = registerUserUseCase.registerUser(command);
        return new ApiResponse<>(ApiResponseMessage.SUCCESS_REQUEST, user);
    }


}
