package com.beomstagram.common.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageRollbackEvent extends Events {

    private String imageName;

    public ImageRollbackEvent(String imageName) {
        super();
        this.imageName = imageName;
    }
}
