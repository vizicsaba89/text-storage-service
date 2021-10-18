package hu.vizicsaba.betvictortask.textstorageservice.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextProcessData {

    @Id
    private Long id;

    private String mostFrequentWord;

    private Integer averageParagraphSize;

    private Double averageParagraphProcessingTime;

    private Long totalProcessingTime;

}
