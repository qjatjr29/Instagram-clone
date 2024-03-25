package com.beomstagram.comment.adapter.in.web;

import com.beomstagram.comment.application.port.in.UpdateCommentCommand;
import com.beomstagram.comment.application.port.in.UpdateCommentUseCase;
import com.beomstagram.comment.domain.Comment;
import com.beomstagram.common.ApiResponse;
import com.beomstagram.common.ApiResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment-service")
@RequiredArgsConstructor
public class UpdateCommentController {

    private final UpdateCommentUseCase updateCommentUseCase;

    @PutMapping("/{commentId}")
    public ApiResponse<Comment> updateComment(@PathVariable("commentId") Long commentId,
                                              @RequestBody CommentRequest commentRequest,
                                              @RequestHeader("userId") Long userId) {

        UpdateCommentCommand command = UpdateCommentCommand.builder()
                .commentId(commentId)
                .userId(userId)
                .content(commentRequest.comment())
                .build();

        Comment comment = updateCommentUseCase.updateComment(command);

        return new ApiResponse<>(ApiResponseMessage.SUCCESS_REQUEST, comment);
    }

}
