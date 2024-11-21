package _4.NovemberRecipeMarket.domain.dto.recipeItem;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecipeItemGetResponse {
    private Long itemId;
    private String itemName;
    private int quantity;
}
