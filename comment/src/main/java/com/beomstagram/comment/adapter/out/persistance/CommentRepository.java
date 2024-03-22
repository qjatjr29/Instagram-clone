package com.beomstagram.comment.adapter.out.persistance;

import java.util.List;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends Neo4jRepository<CommentEntity, Long> {

    @Query("MATCH (c:comment)-[:REPLY]->(r:reply)"
            + "WHERE c.post_type = 'FEED' AND c.post_id = $feedId AND NOT c.is_deleted "
            + "RETURN c")
    List<CommentEntity> findAllByFeedId(Long feedId);
}
