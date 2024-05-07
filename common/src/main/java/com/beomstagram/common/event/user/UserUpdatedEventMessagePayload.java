package com.beomstagram.common.event.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nullable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
