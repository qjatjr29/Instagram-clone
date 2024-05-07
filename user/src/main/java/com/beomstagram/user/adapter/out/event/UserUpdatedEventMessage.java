package com.beomstagram.user.adapter.out.event;

import com.beomstagram.common.event.user.UserNameUpdatedEvent;
import com.beomstagram.common.event.user.UserProfileImageUpdatedEvent;
import com.beomstagram.common.event.user.UserUpdateType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_updated_event_message")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUpdatedEventMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventMessageId;

    private Long userId;

    private String username;
    private String profileImage;

    private UserUpdateType userUpdateType;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EventRecordStatus eventRecordStatus = EventRecordStatus.INIT;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();


    public static UserUpdatedEventMessage fromEvent(UserNameUpdatedEvent event) {
        return UserUpdatedEventMessage.builder()
                .userId(event.getUserId())
                .username(event.getUsername())
                .userUpdateType(event.getUserUpdateType())
                .build();
    }

    public static UserUpdatedEventMessage fromEvent(UserProfileImageUpdatedEvent event) {
        return UserUpdatedEventMessage.builder()
                .userId(event.getUserId())
                .profileImage(event.getProfileImage())
                .userUpdateType(event.getUserUpdateType())
                .build();
    }

    public void success() {
        this.eventRecordStatus = EventRecordStatus.SEND_SUCCESS;
    }

    public void fail() {
        this.eventRecordStatus = EventRecordStatus.SEND_FAIL;
    }
}
