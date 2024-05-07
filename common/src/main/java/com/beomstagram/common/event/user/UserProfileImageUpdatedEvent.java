package com.beomstagram.common.event.user;

import lombok.Getter;

@Getter
public class UserProfileImageUpdatedEvent extends UserUpdatedEvent {

    private final String profileImage;
    private final UserUpdateType userUpdateType;

    public UserProfileImageUpdatedEvent(Long userId, String profileImage) {
        super(userId);
        this.profileImage = profileImage;
        this.userUpdateType = UserUpdateType.PROFILE_IMAGE;
    }

}
