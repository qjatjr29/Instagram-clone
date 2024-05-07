package com.beomstagram.user.adapter.out.kafka;

import com.beomstagram.common.event.user.UserNameUpdatedEvent;
import com.beomstagram.common.event.user.UserProfileImageUpdatedEvent;
import com.beomstagram.common.event.user.UserUpdateType;
import jakarta.annotation.Nullable;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class UserUpdatedEventMessagePayload implements Serializable {

    @NonNull
    private Long userId;

    @Nullable
    private String username;

    @Nullable
    private String profileImage;

    private UserUpdateType userUpdateType;

    public static UserUpdatedEventMessagePayload from(UserNameUpdatedEvent event) {
        return UserUpdatedEventMessagePayload.builder()
                .userId(event.getUserId())
                .username(event.getUsername())
                .userUpdateType(event.getUserUpdateType())
                .build();
    }

    public static UserUpdatedEventMessagePayload from(UserProfileImageUpdatedEvent event) {
        return UserUpdatedEventMessagePayload.builder()
                .userId(event.getUserId())
                .profileImage(event.getProfileImage())
                .userUpdateType(event.getUserUpdateType())
                .build();
    }

}
