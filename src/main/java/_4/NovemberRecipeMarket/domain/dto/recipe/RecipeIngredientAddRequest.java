package _4.NovemberRecipeMarket.domain.dto.recipe;

import lombok.Getter;

import java.util.List;

@Getter

public class RecipeIngredientAddRequest {
    List<Long> items;
}
