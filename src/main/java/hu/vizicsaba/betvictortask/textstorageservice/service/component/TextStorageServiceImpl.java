package hu.vizicsaba.betvictortask.textstorageservice.service.component;

import hu.vizicsaba.betvictortask.textstorageservice.data.model.TextProcessData;
import hu.vizicsaba.betvictortask.textstorageservice.data.repository.TextStorageRepository;
import hu.vizicsaba.betvictortask.textstorageservice.service.model.TextStorageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class TextStorageServiceImpl implements TextStorageService {

    @Autowired
    private TextStorageRepository textStorageRepository;

    @Override
    public Flux<TextStorageResponse> getTextStorageResponse(Integer numberOfResults) {
        return textStorageRepository.getTextProcessData(numberOfResults)
            .map(this::mapTextStorage);
    }

    private TextStorageResponse mapTextStorage(TextProcessData textProcessData) {
        return new TextStorageResponse(
                textProcessData.getId(),
                textProcessData.getMostFrequentWord(),
                textProcessData.getAverageParagraphSize(),
                textProcessData.getAverageParagraphProcessingTime(),
                textProcessData.getTotalProcessingTime()
        );
    }

}
