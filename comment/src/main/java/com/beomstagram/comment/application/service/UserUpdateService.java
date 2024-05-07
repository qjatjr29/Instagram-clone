package com.beomstagram.comment.application.service;

import com.beomstagram.comment.adapter.out.persistance.CommentEntity;
import com.beomstagram.comment.adapter.out.persistance.CommentMapper;
import com.beomstagram.comment.application.port.in.UpdateUserCommand;
import com.beomstagram.comment.application.port.in.UpdateUserUseCase;
import com.beomstagram.comment.application.port.out.UpdateCommentPort;
import com.beomstagram.comment.domain.Comment;
import com.beomstagram.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class UserUpdateService implements UpdateUserUseCase {

    private final UpdateCommentPort updateCommentPort;

    @Override
    public void updateUserInfo(UpdateUserCommand command) {

        updateCommentPort.updateUserInfo(command.getUserId(), command.getUsername(), command.getProfileImage());
    }
}
