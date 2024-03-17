package com.beomstagram.feed.adapter.out.event;

import com.beomstagram.feed.application.port.in.RollbackImageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class UploadImageRollbackEventListener {

    private final RollbackImageUseCase rollbackImageUseCase;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void rollbackUploadImage(UploadImageRollbackEvent event) {
        for(String rollbackImageName : event.getFilenames()) {
            rollbackImageUseCase.rollbackImage(rollbackImageName);
        }
    }
}
