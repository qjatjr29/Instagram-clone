package com.beomstagram.feed.adapter.out.persistence;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


@Entity
@Table(name = "feed")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE feed SET is_deleted = true WHERE id = ?")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class FeedEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Long feedId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "caption")
    private String caption;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "feed_image", joinColumns = @JoinColumn(name = "feed_id"))
    @Builder.Default
    private List<FeedImageEntity> images = new ArrayList<>();

    @Column(name = "like_count")
    @Builder.Default
    private Long likeCount = 0L;

    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = Boolean.FALSE;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "updated_at", updatable = true)
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

}
