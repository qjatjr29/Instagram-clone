package com.beomstagram.comment.adapter.in.web;

import com.beomstagram.comment.application.port.in.DeleteReplyCommand;
import com.beomstagram.comment.application.port.in.ReplyCommand;
import com.beomstagram.comment.application.port.in.ReplyUseCase;
import com.beomstagram.comment.application.port.in.UpdateReplyCommand;
import com.beomstagram.comment.domain.Reply;
import com.beomstagram.common.ApiResponse;
import com.beomstagram.common.ApiResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ApiResponse<Reply> reply(@RequestParam("comment") Long commentId,
                                    @RequestBody ReplyRequest replyRequest,
                                    @RequestHeader("userId") Long userId) {

        ReplyCommand command = ReplyCommand.builder()
                .commentId(commentId)
                .userId(userId)
                .content(replyRequest.content())
                .build();

        Reply reply = replyUseCase.reply(command);

        return new ApiResponse<>(ApiResponseMessage.SUCCESS_REQUEST, reply);
    }

    @PutMapping("/reply/{replyId}")
    public ApiResponse<Reply> modifyReply(@PathVariable("replyId") Long replyId,
                                            @RequestBody ReplyRequest replyRequest,
                                            @RequestHeader("userId") Long userId) {

        UpdateReplyCommand command = UpdateReplyCommand.builder()
                .replyId(replyId)
                .userId(userId)
                .content(replyRequest.content())
                .build();

        Reply reply = replyUseCase.updateReply(command);

        return new ApiResponse<>(ApiResponseMessage.SUCCESS_REQUEST, reply);
    }

    @DeleteMapping("/reply/{replyId}")
    public ApiResponse<Void> deleteReply(@PathVariable("replyId") Long replyId,
                                         @RequestHeader("userId") Long userId) {

        DeleteReplyCommand command = DeleteReplyCommand.builder()
                .replyId(replyId)
                .userId(userId)
                .build();

        replyUseCase.deleteReply(command);

        return new ApiResponse<>(ApiResponseMessage.SUCCESS_REQUEST);
    }
}
