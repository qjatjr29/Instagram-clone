package com.beomstagram.user.adapter.out.event;

import com.beomstagram.common.event.UserNameUpdatedEvent;
import com.beomstagram.common.event.UserProfileImageUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class UserUpdateExternalRecordListener {

    private final UserUpdateExternalEventRecorder eventRecorder;

    // 1. 유저 업데이트 이벤트 outbox 테이블에 저장.
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void recordMessageHandler(UserNameUpdatedEvent event) {
        eventRecorder.save(UserUpdatedEventMessage.fromEvent(event));
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void recordMessageHandler(UserProfileImageUpdatedEvent event) {
        eventRecorder.save(UserUpdatedEventMessage.fromEvent(event));
    }

}
