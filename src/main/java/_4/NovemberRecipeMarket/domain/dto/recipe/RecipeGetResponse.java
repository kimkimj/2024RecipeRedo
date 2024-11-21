package _4.NovemberRecipeMarket.domain.dto.recipe;

import _4.NovemberRecipeMarket.domain.dto.recipeItem.RecipeItemGetResponse;
import _4.NovemberRecipeMarket.domain.dto.review.ReviewResponseForList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class RecipeGetResponse {

    private Long recipeId;
    private String author;
    private String title;

    private String content;

    private List<RecipeItemGetResponse> ingredients;

    private List<ReviewResponseForList> reviews;

    private Integer viewCount;
    private Integer likeCount;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
