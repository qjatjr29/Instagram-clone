package com.beomstagram.user.adapter.out.kafka;

import com.beomstagram.common.event.UserNameUpdatedEvent;
import com.beomstagram.common.event.UserProfileImageUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class UserUpdatedEventMessageListener {

    private final UserUpdatedEventSendService sendService;

    // 2. 카프카에 메시지 전송 (TransactionPhase.AFTER_COMMIT)
    @Async()
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendMessageHandler(UserNameUpdatedEvent event) {
        sendService.send(UserUpdatedEventMessagePayload.from(event));
    }

    @Async()
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendMessageHandler(UserProfileImageUpdatedEvent event) {
        sendService.send(UserUpdatedEventMessagePayload.from(event));
    }

}
