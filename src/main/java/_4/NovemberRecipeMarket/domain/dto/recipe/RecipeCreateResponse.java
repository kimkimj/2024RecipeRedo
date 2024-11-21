package _4.NovemberRecipeMarket.domain.dto.recipe;


import lombok.Getter;

@Getter
public class RecipeCreateResponse {
    private Long recipeId;
    private String message;

    public RecipeCreateResponse(Long recipeId) {
        this.recipeId = recipeId;
        message = "레시피를 등록했습니다.";
    }
}
