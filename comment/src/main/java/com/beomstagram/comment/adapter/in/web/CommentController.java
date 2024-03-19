package com.beomstagram.comment.adapter.in.web;

import com.beomstagram.comment.application.port.in.CommentCommand;
import com.beomstagram.comment.application.port.in.CommentUseCase;
import com.beomstagram.comment.domain.Comment;
import com.beomstagram.common.ApiResponse;
import com.beomstagram.common.ApiResponseMessage;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment-service")
@RequiredArgsConstructor
public class CommentController {

    private final CommentUseCase commentUseCase;

    @PostMapping("/feed/{feedId}")
    public ApiResponse<Comment> comment(
            @PathVariable("feedId") Long feedId,
            @RequestBody CommentRequest commentRequest,
            @Nullable @RequestHeader("username") String username,
            @Nullable @RequestHeader("userId") Long userId) {

        CommentCommand command = CommentCommand.builder()
                .username(username)
                .postId(feedId)
                .userId(userId)
                .content(commentRequest.content())
                .build();

        Comment comment = commentUseCase.commentFeed(command);

        return new ApiResponse<>(ApiResponseMessage.SUCCESS_REQUEST, comment);
    }

}
