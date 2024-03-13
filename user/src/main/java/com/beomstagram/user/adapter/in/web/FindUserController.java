package com.beomstagram.user.adapter.in.web;

import com.beomstagram.common.ApiResponse;
import com.beomstagram.common.ApiResponseMessage;
import com.beomstagram.common.annotation.WebAdapter;
import com.beomstagram.user.application.port.in.FindUserUseCase;
import com.beomstagram.user.domain.User;
import jakarta.annotation.Nullable;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class FindUserController {

    private final FindUserUseCase findUserUseCase;

    @GetMapping("/{userId}")
    public User findUser(@PathVariable("userId") Long userId, @RequestHeader("userId") @Nullable String id) {
        return findUserUseCase.findByUserId(userId);
    }

    // todo : GraphQL을 통해 원하는 데이터만 가져가도록 한다.
    @GetMapping()
    public ApiResponse<Page<FindUserResultDto>> searchUserByNameAndNickName(
            @RequestParam("userId") Long userId,
            @RequestParam("keyword") String keyword,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<FindUserResultDto> users = findUserUseCase.findAllByNameOrNickName(userId, keyword, pageable);
        return new ApiResponse<>(ApiResponseMessage.SUCCESS_REQUEST, users);
    }

    @GetMapping("/exists/{userId}")
    public Boolean isExistsUser(@PathVariable("userId") Long userId) {
        return findUserUseCase.isExistsUser(userId);
    }

}
