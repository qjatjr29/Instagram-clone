package com.beomstagram.user.adapter.out.event;

import com.beomstagram.common.event.user.UserUpdateType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserUpdateExternalEventRecorder {

    private final UserUpdateExternalEventRepository updateExternalEventRepository;

    public void save(UserUpdatedEventMessage userUpdatedEventMessage) {
        updateExternalEventRepository.save(userUpdatedEventMessage);
    }

    public void sendFail(Long userId, UserUpdateType userUpdateType) {
        UserUpdatedEventMessage userUpdatedEventMessage = updateExternalEventRepository.findByUserIdAndUserUpdateType(
                        userId, userUpdateType)
                .orElseThrow(IllegalArgumentException::new);

        userUpdatedEventMessage.fail();
        updateExternalEventRepository.save(userUpdatedEventMessage);
    }

    public void sendSuccess(Long userId, UserUpdateType userUpdateType) {
        // todo: custom exception
        UserUpdatedEventMessage userUpdatedEventMessage = updateExternalEventRepository.findByUserIdAndUserUpdateType(
                        userId, userUpdateType)
                .orElseThrow(IllegalArgumentException::new);

        userUpdatedEventMessage.success();
        updateExternalEventRepository.save(userUpdatedEventMessage);
    }

}
