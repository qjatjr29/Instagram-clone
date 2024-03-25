package com.beomstagram.comment.application.service;

import com.beomstagram.comment.adapter.out.persistance.ReplyEntity;
import com.beomstagram.comment.adapter.out.persistance.ReplyMapper;
import com.beomstagram.comment.application.port.in.ReplyCommand;
import com.beomstagram.comment.application.port.in.ReplyUseCase;
import com.beomstagram.comment.application.port.in.UpdateReplyCommand;
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

    private final ReplyPort replyPort;
    private final UpdateCommentPort updateCommentPort;
    private final UpdateReplyPort updateReplyPort;
    private final ReplyMapper replyMapper;

    @Override
    public Reply reply(ReplyCommand command) {

        ReplyEntity replyEntity = replyPort.reply(command.getUserId(), command.getContent());
        //todo : transactionEventListener
        updateCommentPort.reply(command.getCommentId(), replyEntity);

        return replyMapper.mapToDomain(replyEntity);
    }

    @Override
    public Reply updateReply(UpdateReplyCommand command) {

        ReplyEntity replyEntity = updateReplyPort
                .updateReply(command.getReplyId(), command.getUserId(), command.getContent());

        return replyMapper.mapToDomain(replyEntity);
    }

}
