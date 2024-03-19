package com.beomstagram.comment.adapter.out.persistance;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReplyEntity implements Serializable {

    @Column(name = "userId")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "reply")
    private String reply;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReplyEntity that = (ReplyEntity) o;
        return Objects.equals(userId, that.userId) && Objects.equals(username, that.username)
                && Objects.equals(reply, that.reply);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, reply);
    }
}
