package com.beomstagram.comment.adapter.out.kafka;

import com.beomstagram.comment.application.port.in.UpdateUserCommand;
import com.beomstagram.comment.application.port.in.UpdateUserUseCase;
import com.beomstagram.common.event.user.UserUpdatedEventMessagePayload;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUpdateConsumer {

    private final UpdateUserUseCase updateUserUseCase;


    @KafkaListener(topics = "${kafka.topic.user-updated}",
            groupId = "${spring.kafka.consumer.groupId}",
            containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, UserUpdatedEventMessagePayload> message) {
        try {
            UserUpdatedEventMessagePayload userUpdatedEventMessagePayload = message.value();

            // 해당 유저 아이디를 가진 comment들의 유저 정보 업데이트
            UpdateUserCommand command = UpdateUserCommand.builder()
                    .userId(userUpdatedEventMessagePayload.getUserId())
                    .username(userUpdatedEventMessagePayload.getUsername())
                    .profileImage(userUpdatedEventMessagePayload.getProfileImage())
                    .build();

            updateUserUseCase.updateUserInfo(command);
        } catch(Exception e) {
            // todo: custom exception
            throw new RuntimeException();
        }
    }
}

