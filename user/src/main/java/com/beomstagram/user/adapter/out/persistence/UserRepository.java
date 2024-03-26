package com.beomstagram.user.adapter.out.persistence;

import java.util.List;
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

    UserEntity findByEmail(String userEmail);

    @Query("MATCH (user:user)-[:FOLLOWER]->(follower:user) WHERE id(user) = $userId RETURN collect(follower)")
    List<UserEntity> findFollowersByUserId(@Param("userId") Long userId);

    @Query("MATCH (user:user)-[:FOLLOWING]->(following:user) WHERE id(user) = $userId RETURN collect(following)")
    List<UserEntity> findFollowingByUserId(@Param("userId") Long userId);
}
