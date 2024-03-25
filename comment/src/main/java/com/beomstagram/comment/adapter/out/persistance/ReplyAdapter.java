package com.beomstagram.comment.adapter.out.persistance;

import com.beomstagram.comment.application.port.out.ReplyPort;
import com.beomstagram.comment.application.port.out.UpdateReplyPort;
import com.beomstagram.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ReplyAdapter implements ReplyPort, UpdateReplyPort {

    private final ReplyRepository replyRepository;

    @Override
    public ReplyEntity reply(Long userId, String content) {

        ReplyEntity replyEntity = ReplyEntity.builder()
                .userId(userId)
                .content(content)
                .build();

        return replyRepository.save(replyEntity);
    }

    @Override
    public ReplyEntity updateReply(Long replyId, Long userId, String content) {

        ReplyEntity replyEntity = replyRepository.findByIdAndUserId(replyId, userId)
                        .orElseThrow(() -> new IllegalArgumentException());

        replyEntity.updateContent(content);
        return replyRepository.save(replyEntity);
    }
}
