package com.beomstagram.comment.adapter.in.web;

import com.beomstagram.comment.application.port.in.FindCommentUseCase;
import com.beomstagram.comment.domain.Comment;
import com.beomstagram.common.ApiResponse;
import com.beomstagram.common.ApiResponseMessage;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment-service")
@RequiredArgsConstructor
public class FindCommentController {

    private final FindCommentUseCase findCommentUseCase;

    @GetMapping("/comments")
    public ApiResponse<Page<Comment>> findAllByFeedId(@NotNull @RequestParam("feed") Long feedId,
                                                      @PageableDefault(page = 0, size = 10)Pageable pageable) {

        Page<Comment> comments = findCommentUseCase.findAllByFeedId(feedId, pageable);

        return new ApiResponse<>(ApiResponseMessage.SUCCESS_REQUEST, comments);
    }

    @GetMapping("/{commentId}")
    public ApiResponse<Comment> findById(@PathVariable("commentId") Long commentId) {

        Comment comment = findCommentUseCase.findById(commentId);

        return new ApiResponse<>(ApiResponseMessage.SUCCESS_REQUEST, comment);
    }

}
