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
    public ReplyEntity reply(Long commentId, Long userId, String username, String profileImage, String content) {
        ReplyEntity replyEntity = ReplyEntity.builder()
                .commentId(commentId)
                .userId(userId)
                .username(username)
                .profileImage(profileImage)
                .content(content)
                .build();

        return replyRepository.save(replyEntity);
    }

    @Override
    public ReplyEntity updateReply(Long replyId, Long userId, String content) {

        ReplyEntity replyEntity = replyRepository.findById(replyId)
                .orElseThrow(() -> new IllegalArgumentException());

        if(!replyEntity.isWriter(userId)) {
            throw new IllegalArgumentException();
        }

        replyEntity.updateContent(content);
        return replyRepository.save(replyEntity);
    }

    @Override
    public void deleteById(Long replyId, Long userId) {

        ReplyEntity replyEntity = replyRepository.findById(replyId)
                .orElseThrow(() -> new IllegalArgumentException());

        if(!replyEntity.isWriter(userId)) {
            throw new IllegalArgumentException();
        }

        replyRepository.deleteById(replyId);
    }

    @Override
    public void deleteByCommentId(Long commentId) {
        replyRepository.deleteByCommentId(commentId);
    }
}
