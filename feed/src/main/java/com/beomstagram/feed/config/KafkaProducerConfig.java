package com.beomstagram.feed.config;

import com.beomstagram.common.event.ImageRollbackEvent;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfig {

    private final String bootstrapServers;
    private final String imageRollbackTopic;

    public KafkaProducerConfig(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
                               @Value("${kafka.topic.image.rollback}") String topicName) {
        this.bootstrapServers = bootstrapServers;
        this.imageRollbackTopic = topicName;
    }

    @Bean
    public ProducerFactory<String, ImageRollbackEvent> uploadedImageRollbackEventProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(configs);
    }

    @Bean
    public KafkaTemplate<String, ImageRollbackEvent> imageRollbackEventKafkaTemplate() {
        return new KafkaTemplate<>(uploadedImageRollbackEventProducerFactory());
    }

    @Bean
    public NewTopic uploadedImageRollbackTopic() {
        return new NewTopic(imageRollbackTopic, 1, (short) 1);
    }

}