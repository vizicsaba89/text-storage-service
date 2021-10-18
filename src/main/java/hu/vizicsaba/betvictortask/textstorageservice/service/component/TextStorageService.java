package hu.vizicsaba.betvictortask.textstorageservice.service.component;

import hu.vizicsaba.betvictortask.textstorageservice.service.model.TextStorageResponse;
import reactor.core.publisher.Flux;

public interface TextStorageService {

    Flux<TextStorageResponse> getTextStorageResponse(Integer numberOfResults);

}
