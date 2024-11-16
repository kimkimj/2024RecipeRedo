package _4.NovemberRecipeMarket.domain.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ReviewRequest {
    private String title;
    private String content;
}
