package com.beomstagram.common.config;

import com.beomstagram.common.event.Events;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EventConfig {

    private final ApplicationContext applicationContext;

    @Bean
    public InitializingBean eventsInitializer() {
        return () -> Events.setPublisher(applicationContext);
    }

}
