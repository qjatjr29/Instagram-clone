package com.beomstagram.comment.adapter.out.persistance;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.RelationshipId;

@Node("comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class CommentEntity implements Serializable {

    @RelationshipId
    private Long id;

    @Property(name = "post_id")
    private Long postId;

    @Property(name = "user_id")
    private Long userId;

    @Property(name = "user_name")
    private String username;

    @Property(name = "profile_image")
    private String profileImage;

    @Property(name = "content")
    private String content;

    @Property(name = "post_type")
    private PostType postType;

    @Property(name = "reply_count")
    @Builder.Default
    private Long replyCount = 0L;

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

    @Relationship(type = "REPLY", direction = Relationship.Direction.OUTGOING)
    @Builder.Default
    private List<ReplyEntity> replyList = new ArrayList<>();

    public void addReply(final ReplyEntity replyEntity) {
        this.replyList.add(replyEntity);
        this.replyCount += 1;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateUserInfo(String username, String profileImage) {
        if(!isBlank(username)) {
            this.username = username;
        }
        if(!isBlank(profileImage)) {
            this.profileImage = profileImage;
        }
    }

    private boolean isBlank(String target) {
        return Strings.isBlank(target);
    }
}
