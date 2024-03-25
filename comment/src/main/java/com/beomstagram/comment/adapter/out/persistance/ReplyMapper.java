package com.beomstagram.comment.adapter.out.persistance;

import com.beomstagram.comment.domain.Reply;
import org.springframework.stereotype.Component;

@Component
public class ReplyMapper {

    public Reply mapToDomain(ReplyEntity replyEntity) {
        return Reply.generateReply(replyEntity.getId(),
                replyEntity.getUserId(),
                replyEntity.getContent(),
                replyEntity.getCreatedAt());
    }

}
