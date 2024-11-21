package _4.NovemberRecipeMarket.domain.dto.recipe;

import _4.NovemberRecipeMarket.domain.dto.recipeItem.RecipeItemGetResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RecipeUpdateResponse {
    private Long id;
    private String title;
    private String content;
    private List<RecipeItemGetResponse> ingredients;
}
