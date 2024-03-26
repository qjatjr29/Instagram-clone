package com.beomstagram.user.adapter.out.persistence;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.RelationshipId;

@Node("user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class UserEntity {

//    @Id
//    @GeneratedValue
    @RelationshipId
    private Long id;

    @Property("email")
    private String email;

    @Property("name")
    private String name;

    @Property("nickname")
    private String nickname;

    @Property("phone_number")
    private String phoneNumber;

    @Property("password")
    private String password;

    @Property("profile_image")
    private String profileImage;

    @Relationship(type = "FOLLOWING")
    @Builder.Default
    private List<UserEntity> following = new ArrayList<>();

    @Relationship(type = "FOLLOWER")
    @Builder.Default
    private List<UserEntity> followers = new ArrayList<>();

    @Property("is_deleted")
    @Builder.Default
    private Boolean isDeleted = Boolean.FALSE;

    @Property("created_at")
    @CreatedDate
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Property("updated_at")
    @LastModifiedBy
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    private String refreshToken;

    public void updateName(String newName) {
        this.name = newName;
    }

    public void checkPwd(String password) {
        // todo : custom exception
        if(!this.password.equals(password)) {
            throw new RuntimeException();
        }
    }

    public void updateRefreshToken(final String token) {
        this.refreshToken = token;
    }
}
