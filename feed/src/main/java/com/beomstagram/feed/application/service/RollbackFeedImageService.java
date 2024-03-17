package com.beomstagram.feed.application.service;

import com.beomstagram.common.annotation.UseCase;
import com.beomstagram.common.event.Events;
import com.beomstagram.common.event.ImageRollbackEvent;
import com.beomstagram.feed.application.port.in.RollbackImageUseCase;

@UseCase
public class RollbackFeedImageService implements RollbackImageUseCase {

    @Override
    public void rollbackImage(String imageName) {
        ImageRollbackEvent imageRollbackEvent = new ImageRollbackEvent(imageName);
        Events.raise(imageRollbackEvent);
    }
}
