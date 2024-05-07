package com.beomstagram.common.event.user;

import com.beomstagram.common.event.Events;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class UserUpdatedEvent extends Events {
    @NonNull
    private Long userId;
}
