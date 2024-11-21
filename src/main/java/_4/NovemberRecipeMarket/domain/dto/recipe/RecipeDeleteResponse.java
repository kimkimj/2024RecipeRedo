package _4.NovemberRecipeMarket.domain.dto.recipe;

import lombok.Getter;

@Getter
public class RecipeDeleteResponse {

    private Long id;
    private String title;
    private String message;

    public RecipeDeleteResponse(Long id, String title) {
        this.id = id;
        this.title = title;
        message = "레시피를 삭제했습니다.";
    }
}
