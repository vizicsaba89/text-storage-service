package hu.vizicsaba.betvictortask.textstorageservice.configuration;

import hu.vizicsaba.betvictortask.textstorageservice.service.model.TextProcessResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ReceiverOptions<String, TextProcessResult> kafkaReceiverOptions(
        @Value(value = "${configuration.kafka.topic}") String topic,
        KafkaProperties kafkaProperties
    ) {
        ReceiverOptions<String, TextProcessResult> basicReceiverOptions = ReceiverOptions.create(kafkaProperties.buildConsumerProperties());

        return basicReceiverOptions.subscription(Collections.singletonList(topic));
    }

    @Bean
    public ReactiveKafkaConsumerTemplate<String, TextProcessResult> reactiveKafkaConsumerTemplate(
        ReceiverOptions<String, TextProcessResult> kafkaReceiverOptions
    ) {
        return new ReactiveKafkaConsumerTemplate<>(kafkaReceiverOptions);
    }

}
