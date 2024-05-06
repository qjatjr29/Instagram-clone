package com.beomstagram.common.event;

import lombok.Getter;

@Getter

public class UserNameUpdatedEvent extends UserUpdatedEvent {

    private final String username;
    private final UserUpdateType userUpdateType;

    public UserNameUpdatedEvent(Long userId, String username) {
        super(userId);
        this.username = username;
        this.userUpdateType = UserUpdateType.NAME;
    }
}
