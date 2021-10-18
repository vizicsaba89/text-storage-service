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
        consumeTextProcessData().subscribe();
    }

    private Flux<TextProcessData> consumeTextProcessData() {
        return reactiveKafkaConsumerTemplate.receiveAutoAck()
                .map(ConsumerRecord::value)
                .map(this::getTextProcessData)
                .flatMap(textStorageRepository::save)
                .doOnNext(fakeConsumerDTO -> log.info("successfully consumed {}={}", TextProcessResult.class.getSimpleName(), fakeConsumerDTO))
                .doOnError(throwable -> log.error("something bad happened while consuming : {}", throwable.getMessage()));
    }

    private TextProcessData getTextProcessData(TextProcessResult textProcessResult) {
        return new TextProcessData(
                null,
                textProcessResult.getMostFrequentWord(),
                textProcessResult.getAverageParagraphSize(),
                textProcessResult.getAverageParagraphProcessingTime(),
                textProcessResult.getTotalProcessingTime());
    }

}

