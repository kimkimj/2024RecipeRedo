package _4.NovemberRecipeMarket.domain.dto.item;

import lombok.Getter;

@Getter
public class ItemDeleteResponse {
    private Long id;
    private String message;

    public ItemDeleteResponse(Long id) {
        this.id = id;
        this.message = "상품이 삭제되었습니다.";
    }
}
