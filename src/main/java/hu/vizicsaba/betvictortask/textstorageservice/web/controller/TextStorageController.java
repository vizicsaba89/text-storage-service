package hu.vizicsaba.betvictortask.textstorageservice.web.controller;

import hu.vizicsaba.betvictortask.textstorageservice.service.component.TextStorageService;
import hu.vizicsaba.betvictortask.textstorageservice.service.model.TextStorageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("betvictor")
@CrossOrigin
public class TextStorageController {

    @Autowired
    private TextStorageService textStorageService;

    @GetMapping(path = "/history", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Flux<TextStorageResponse> getTextStorageResponse(@RequestParam(value = "num_of_results", defaultValue = "10") Integer numberOfResults) {
        return textStorageService.getTextStorageResponse(numberOfResults);
    }
}
