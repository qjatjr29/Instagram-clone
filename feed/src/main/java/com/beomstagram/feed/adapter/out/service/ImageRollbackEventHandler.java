package com.beomstagram.feed.adapter.out.service;

import com.beomstagram.common.event.ImageRollbackEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ImageRollbackEventHandler {

    private final String imageRollbackTopic;
    private final KafkaTemplate<String, ImageRollbackEvent> imageRollbackEventKafkaTemplate;

    public ImageRollbackEventHandler(@Value("${kafka.topic.image.rollback}") String topicName,
                                     KafkaTemplate<String, ImageRollbackEvent> imageRollbackEventKafkaTemplate) {
        this.imageRollbackTopic = topicName;
        this.imageRollbackEventKafkaTemplate = imageRollbackEventKafkaTemplate;
    }

    @EventListener(ImageRollbackEvent.class)
    public void handle(ImageRollbackEvent event) {

        imageRollbackEventKafkaTemplate.send(imageRollbackTopic, event);
    }

}
