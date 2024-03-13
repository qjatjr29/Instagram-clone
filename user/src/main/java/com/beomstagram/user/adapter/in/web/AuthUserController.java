package com.beomstagram.user.adapter.in.web;

import com.beomstagram.common.ApiResponse;
import com.beomstagram.common.ApiResponseMessage;
import com.beomstagram.common.annotation.WebAdapter;
import com.beomstagram.user.application.port.in.AuthUserUseCase;
import com.beomstagram.user.application.port.in.LoginUserCommand;
import com.beomstagram.user.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class AuthUserController {

    private final AuthUserUseCase authUserUseCase;

    @PostMapping("/login")
    public ApiResponse<JwtToken> login(@RequestBody LoginUserRequest loginUserRequest) {

        LoginUserCommand command = LoginUserCommand
                .builder()
                .userEmail(loginUserRequest.getUserEmail())
                .password(loginUserRequest.getPassword())
                .build();

        return new ApiResponse<>(ApiResponseMessage.SUCCESS_REQUEST, authUserUseCase.login(command));
    }

}
