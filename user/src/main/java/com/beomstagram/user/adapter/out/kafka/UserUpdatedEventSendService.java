package com.beomstagram.user.adapter.out.kafka;

import com.beomstagram.common.event.user.UserUpdatedEventMessagePayload;
import com.beomstagram.user.adapter.out.event.UserUpdateExternalEventRecorder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUpdatedEventSendService {

    @Value("${kafka.topic.user-updated}")
    private String USER_UPDATE_TOPIC_NAME;

    private final KafkaTemplate<String, UserUpdatedEventMessagePayload> userUpdateEventKafkaTemplate;
    private final UserUpdateExternalEventRecorder userUpdateExternalEventRecorder;

    public void send(UserUpdatedEventMessagePayload messagePayload) {
        userUpdateEventKafkaTemplate.send(USER_UPDATE_TOPIC_NAME, messagePayload)
                .whenComplete((result, ex) -> {
                    if(ex == null) {
                        // 메세지 발행 성공 상태로 변경 로직 추가
                        userUpdateExternalEventRecorder.sendSuccess(
                                messagePayload.getUserId(),
                                messagePayload.getUserUpdateType());
                    }
                    else {
                        // 메세지 발행 실패 상태로 변경 로직을 추가
                        userUpdateExternalEventRecorder.sendFail(
                                messagePayload.getUserId(),
                                messagePayload.getUserUpdateType());
                    }
                });
    }

}
