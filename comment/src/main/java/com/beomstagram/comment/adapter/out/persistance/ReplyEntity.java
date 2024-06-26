package com.beomstagram.comment.adapter.out.persistance;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("reply")
@AllArgsConstructor
@Getter
@Builder
public class ReplyEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "comment_id")
    private Long commentId;

    @Property(name = "user_id")
    private Long userId;

    @Property(name = "user_name")
    private String username;

    @Property(name = "profile_image")
    private String profileImage;

    @Property(name = "reply_content")
    private String content;

    @Property("created_at")
    @CreatedDate
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Property("updated_at")
    @LastModifiedBy
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    public void updateContent(String content) {
        this.content = content;
    }

    public boolean isWriter(Long userId) {
        return Objects.equals(this.userId, userId);
    }
}
