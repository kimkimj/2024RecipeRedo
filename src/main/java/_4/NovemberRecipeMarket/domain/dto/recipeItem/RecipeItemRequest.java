package _4.NovemberRecipeMarket.domain.dto.recipeItem;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecipeItemRequest {
    private Long itemId;
    private int quantity;
}
