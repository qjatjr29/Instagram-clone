package com.beomstagram.feed.application.port.in;

public interface RollbackImageUseCase {
    void rollbackImage(String imageName);
}
