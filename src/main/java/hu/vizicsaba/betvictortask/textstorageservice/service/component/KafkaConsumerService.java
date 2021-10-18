package hu.vizicsaba.betvictortask.textstorageservice.service.component;

import hu.vizicsaba.betvictortask.textstorageservice.data.model.TextProcessData;
import hu.vizicsaba.betvictortask.textstorageservice.data.repository.TextStorageRepository;
import hu.vizicsaba.betvictortask.textstorageservice.service.model.TextProcessResult;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.ReceiverRecord;

import java.time.Duration;


@Log4j2
@Service
public class KafkaConsumerService implements CommandLineRunner {

    @Autowired
    private ReactiveKafkaConsumerTemplate<String, TextProcessResult> reactiveKafkaConsumerTemplate;

    @Autowired
    private TextStorageRepository textStorageRepository;

    @Override
    public void run(String... args) throws Exception {
        consumeTextProcessData();
    }

    private void consumeTextProcessData() {
        Flux<ConsumerRecord<String, TextProcessResult>> consumerRecordFlux = reactiveKafkaConsumerTemplate.receiveAutoAck();

        consumerRecordFlux
                .delayElements(Duration.ofSeconds(5L))
                .subscribe(record -> {
                    log.info("#########", record.value());
                });
    }

    private TextProcessData getTextProcessData() {
        return new TextProcessData(null, "alma", 10, 120.1, 1000L);
    }
}

