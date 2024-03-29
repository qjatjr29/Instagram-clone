package com.beomstagram.comment.adapter.out.persistance;

import java.util.Optional;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends Neo4jRepository<ReplyEntity, Long> {

    Optional<ReplyEntity> findByIdAndUserId(Long replyId, Long userId);

    @Query("MATCH (r:reply) WHERE r.comment_id = $commentId DELETE r")
    void deleteByCommentId(Long commentId);
}
