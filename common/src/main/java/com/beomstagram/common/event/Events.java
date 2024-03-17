package com.beomstagram.common.event;

import java.io.Serializable;
import org.springframework.context.ApplicationEventPublisher;

public class Events implements Serializable {

    private static ApplicationEventPublisher publisher;

    public static void setPublisher(ApplicationEventPublisher publisher) {
        Events.publisher = publisher;
    }

    public static void raise(Object event) {
        if (publisher != null) {
            publisher.publishEvent(event);
        }
    }

}
