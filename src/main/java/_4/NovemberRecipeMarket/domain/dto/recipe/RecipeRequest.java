package _4.NovemberRecipeMarket.domain.dto.recipe;

import _4.NovemberRecipeMarket.domain.dto.recipeItem.RecipeItemRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RecipeRequest {
    private String title;
    private String content;
    private List<RecipeItemRequest> ingredients;

}
