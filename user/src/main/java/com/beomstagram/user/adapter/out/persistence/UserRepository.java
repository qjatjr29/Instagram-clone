package com.beomstagram.user.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends Neo4jRepository<UserEntity, Long> {
    Page<UserEntity> findAllByName(String keyword, Pageable pageable);
    Page<UserEntity> findAllByNameContainingOrNicknameContaining(String name, String nickname, Pageable pageable);

    @Query("MATCH (u:user)-[:FOLLOWS]->(f:user)<-[:FOLLOWS]-(c:user)"
            + " WHERE id(u) = $userId AND id(c) = $targetUserId RETURN count(f)")
    int findCommonFollowersCount(@Param("userId") Long userId, @Param("targetUserId") Long targetUserId);
}
