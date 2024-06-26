package com.beomstagram.comment.adapter.out.persistance;

import java.util.List;
import java.util.Optional;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends Neo4jRepository<CommentEntity, Long>{

    @Query("MATCH (c:comment) "
            + "WHERE c.post_type = 'FEED' AND c.post_id = $feedId AND NOT c.is_deleted "
            + "OPTIONAL MATCH (c)-[r:REPLY]->(p:reply) "
            + "RETURN c, collect(r), collect(p)")
    List<CommentEntity> findAllByFeedId(Long feedId);

    Optional<CommentEntity> findByIdAndUserId(Long commentId, Long userId);

    @Query("MATCH (c:comment)-[r:REPLY]->(p:reply) "
            + "WHERE id(c) = $commentId AND c.user_id = $userId "
            + "DETACH DELETE c, p")
    void deleteByIdAndUserId(Long commentId, Long userId);

    Optional<CommentEntity> findByUserId(Long userId);
}
