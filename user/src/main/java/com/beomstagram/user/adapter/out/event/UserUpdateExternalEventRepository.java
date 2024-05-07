package com.beomstagram.user.adapter.out.event;

import com.beomstagram.common.event.user.UserUpdateType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserUpdateExternalEventRepository extends JpaRepository<UserUpdatedEventMessage, Long> {
    Optional<UserUpdatedEventMessage> findByUserIdAndUserUpdateType(Long userId, UserUpdateType userUpdateType);
}
