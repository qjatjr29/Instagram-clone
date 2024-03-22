package com.beomstagram.comment.adapter.out.persistance;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends Neo4jRepository<ReplyEntity, Long> {

}
