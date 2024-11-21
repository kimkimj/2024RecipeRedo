package _4.NovemberRecipeMarket.domain.dto.recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class RecipeGetResponseForList {
    private Long recipeId;
    private String author;
    private String title;
    private Integer viewCount;
    private Integer likeCount;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
