package com.beomstagram.common.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class UserUpdatedEvent extends Events {
    @NonNull
    private Long userId;
}
