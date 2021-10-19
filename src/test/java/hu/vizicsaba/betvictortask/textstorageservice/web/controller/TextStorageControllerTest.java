package hu.vizicsaba.betvictortask.textstorageservice.web.controller;

import hu.vizicsaba.betvictortask.textstorageservice.service.component.TextStorageService;
import hu.vizicsaba.betvictortask.textstorageservice.service.model.TextStorageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EmbeddedKafka
public class TextStorageControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private TextStorageService textStorageService;

    @BeforeEach
    void setup() {
        Mockito.when(textStorageService.getTextStorageResponse(10))
                .thenReturn(Flux.just(
                    new TextStorageResponse(1L, "ipsum", 10.2, 130.1, 345L)
                ));
    }

    @Test
    @DisplayName("TextStorageController /history endpoint  -> get stored text responses limited by given number of results parameter")
    void givenEndpointForTextStorageHistory_whenEndpointCalled_thenLimitedNumberOfTextStorageResponseShouldReturned() {
        webTestClient
            .get().uri("/betvictor/history?num_of_results=10")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .consumeWith(response -> assertThat(response.getResponseBody() != null));
    }

}
