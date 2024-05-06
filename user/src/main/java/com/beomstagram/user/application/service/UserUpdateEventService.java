package com.beomstagram.user.application.service;

import com.beomstagram.common.event.Events;
import com.beomstagram.common.event.UserNameUpdatedEvent;
import com.beomstagram.common.event.UserProfileImageUpdatedEvent;
import com.beomstagram.user.application.port.in.UserNameUpdateEventCommand;
import com.beomstagram.user.application.port.in.UserProfileImageUpdateEventCommand;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateEventService {

    public void usernameUpdateEventPublish(UserNameUpdateEventCommand command) {
        UserNameUpdatedEvent userUpdatedEvent = new UserNameUpdatedEvent(
                command.getUserId(),
                command.getUsername());

        Events.raise(userUpdatedEvent);
    }

    public void userProfileImageUpdateEventPublish(UserProfileImageUpdateEventCommand command) {
        UserProfileImageUpdatedEvent userUpdatedEvent = new UserProfileImageUpdatedEvent(
                command.getUserId(),
                command.getProfileImage());

        Events.raise(userUpdatedEvent);
    }

}
