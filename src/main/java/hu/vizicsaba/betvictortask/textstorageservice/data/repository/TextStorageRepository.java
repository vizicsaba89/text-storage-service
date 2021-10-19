package hu.vizicsaba.betvictortask.textstorageservice.data.repository;

import hu.vizicsaba.betvictortask.textstorageservice.data.model.TextProcessData;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TextStorageRepository extends ReactiveCrudRepository<TextProcessData, Long> {

    @Query("SELECT * FROM text_process_data ORDER BY id DESC LIMIT :numberOfResults")
    Flux<TextProcessData> getTextProcessData(Integer numberOfResults);
}
