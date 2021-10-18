package hu.vizicsaba.betvictortask.textstorageservice.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextProcessResult {

    @JsonProperty("most_frequent_word")
    private String mostFrequentWord;

    @JsonProperty("avg_paragraph_size")
    private Integer averageParagraphSize;

    @JsonProperty("avg_paragraph_processing_time")
    private Double averageParagraphProcessingTime;

    @JsonProperty("total_processing_time")
    private Long totalProcessingTime;

}
