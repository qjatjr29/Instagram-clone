package com.beomstagram.comment.application.service;

import com.beomstagram.comment.adapter.out.persistance.ReplyEntity;
import com.beomstagram.comment.adapter.out.persistance.ReplyMapper;
import com.beomstagram.comment.adapter.out.service.feign.User;
import com.beomstagram.comment.application.port.in.DeleteReplyCommand;
import com.beomstagram.comment.application.port.in.ReplyCommand;
import com.beomstagram.comment.application.port.in.ReplyUseCase;
import com.beomstagram.comment.application.port.in.UpdateReplyCommand;
import com.beomstagram.comment.application.port.out.FindUserPort;
import com.beomstagram.comment.application.port.out.ReplyPort;
import com.beomstagram.comment.application.port.out.UpdateCommentPort;
import com.beomstagram.comment.application.port.out.UpdateReplyPort;
import com.beomstagram.comment.domain.Reply;
import com.beomstagram.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class ReplyService implements ReplyUseCase {

    private final FindUserPort findUserPort;
    private final ReplyPort replyPort;
    private final UpdateCommentPort updateCommentPort;
    private final UpdateReplyPort updateReplyPort;
    private final ReplyMapper replyMapper;

    @Override
    public Reply reply(ReplyCommand command) {

        User writer = findUserPort.findByUserId(command.getUserId());

        ReplyEntity replyEntity = replyPort.reply(command.getCommentId(),
                command.getUserId(), writer.nickname(), writer.profileImage(), command.getContent());
        updateCommentPort.reply(command.getCommentId(), replyEntity);

        return replyMapper.mapToDomain(replyEntity);
    }

    @Override
    public Reply updateReply(UpdateReplyCommand command) {

        ReplyEntity replyEntity = updateReplyPort
                .updateReply(command.getReplyId(), command.getUserId(), command.getContent());

        return replyMapper.mapToDomain(replyEntity);
    }

    @Override
    public void deleteReply(DeleteReplyCommand command) {
        updateReplyPort.deleteById(command.getReplyId(), command.getUserId());
    }

}
