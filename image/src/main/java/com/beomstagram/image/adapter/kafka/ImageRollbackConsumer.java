package com.beomstagram.image.adapter.kafka;

import com.beomstagram.common.event.ImageRollbackEvent;
import com.beomstagram.image.application.port.in.DeleteImageUseCase;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageRollbackConsumer {

    private final DeleteImageUseCase deleteImageUseCase;

    @KafkaListener(topics = "${kafka.topic.image.rollback}",
            groupId = "${spring.kafka.consumer.groupId}",
            containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, ImageRollbackEvent> record) {
        try {
            ImageRollbackEvent event = record.value();
            String imageName = event.getImageName();
            deleteImageUseCase.deleteImage(imageName);
        } catch(Exception e) {
            throw new RuntimeException();
        }
    }
}

