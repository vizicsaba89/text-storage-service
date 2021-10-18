package hu.vizicsaba.betvictortask.textstorageservice.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextStorageResponse {

    private Long id;

    private String mostFrequentWord;

    private Integer averageParagraphSize;

    private Double averageParagraphProcessingTime;

    private Long totalProcessingTime;

}
