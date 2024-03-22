package com.beomstagram.comment.adapter.out.persistance;

import com.beomstagram.comment.application.port.out.ReplyPort;
import com.beomstagram.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ReplyAdapter implements ReplyPort {

    private final ReplyRepository replyRepository;

    @Override
    public ReplyEntity reply(Long userId, String username, String content) {

        ReplyEntity replyEntity = ReplyEntity.builder()
                .userId(userId)
                .username(username)
                .content(content)
                .build();

        return replyRepository.save(replyEntity);
    }
}
