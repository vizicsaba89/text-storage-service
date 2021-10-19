package hu.vizicsaba.betvictortask.textstorageservice.service.component;

import hu.vizicsaba.betvictortask.textstorageservice.data.model.TextProcessData;
import hu.vizicsaba.betvictortask.textstorageservice.data.repository.TextStorageRepository;
import hu.vizicsaba.betvictortask.textstorageservice.service.model.TextStorageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@SpringBootTest
@DirtiesContext
@EmbeddedKafka
public class TextStorageServiceImplTest {

    @MockBean
    private TextStorageRepository textStorageRepository;

    @Autowired
    private TextStorageService textStorageService;

    @BeforeEach
    void setup() {
        Mockito.when(textStorageRepository.getTextProcessData(3))
                .thenReturn(Flux.fromIterable(getTextStorageResponseList()));
    }

    @Test
    @DisplayName("TextStorageServiceImpl -> get stored text responses limited by given number of results parameter")
    void givenMethodToGetTextStorageResponseLimitedWithNumberOfResults_whenMethodCalled_thenLimitedNumberOfTextStorageResponseShouldReturned() {
        Flux<TextStorageResponse> textStorageResponseFlux = textStorageService.getTextStorageResponse(3);

        StepVerifier.create(textStorageResponseFlux)
            .expectNextMatches(Objects::nonNull)
            .expectNextMatches(Objects::nonNull)
            .expectNextMatches(Objects::nonNull)
            .expectComplete()
            .verify();
    }

    private List<TextProcessData> getTextStorageResponseList() {
        return IntStream.range(0, 3)
                .mapToObj(pos -> getTextStorageResponse())
                .collect(Collectors.toList());
    }

    private TextProcessData getTextStorageResponse() {
        Random random = new Random();

        String randomString = random.ints(97, 122 + 1)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return new TextProcessData(
            ThreadLocalRandom.current().nextLong(0, 100),
            randomString,
            ThreadLocalRandom.current().nextDouble(0, 100),
            ThreadLocalRandom.current().nextDouble(0, 100),
            ThreadLocalRandom.current().nextLong(0, 100)
        );
    }

}
