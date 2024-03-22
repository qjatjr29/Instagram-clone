package com.beomstagram.comment.adapter.in.web;

import com.beomstagram.comment.application.port.in.ReplyCommand;
import com.beomstagram.comment.application.port.in.ReplyUseCase;
import com.beomstagram.comment.domain.Comment;
import com.beomstagram.common.ApiResponse;
import com.beomstagram.common.ApiResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment-service")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyUseCase replyUseCase;

    @PostMapping("/reply")
    public ApiResponse<Comment> reply(@RequestParam("comment") Long commentId,
                                    @RequestBody ReplyRequest replyRequest,
                                    @RequestHeader("username") String username,
                                    @RequestHeader("userId") Long userId) {

        ReplyCommand command = ReplyCommand.builder()
                .commentId(commentId)
                .username(username)
                .userId(userId)
                .content(replyRequest.content())
                .build();

        Comment comment = replyUseCase.reply(command);

        return new ApiResponse<>(ApiResponseMessage.SUCCESS_REQUEST, comment);
    }
}
