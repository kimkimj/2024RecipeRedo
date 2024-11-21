package _4.NovemberRecipeMarket.domain.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReviewResponseForList {
    private Long reviewId;
    private String author;
    private String title;
    private String content;
    private LocalDateTime createdDate;
}
