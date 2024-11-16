package _4.NovemberRecipeMarket.domain.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewResponse {
    private Long reviewId;
    private String author;
    private String title;
    private String content;
    private String message;
}
