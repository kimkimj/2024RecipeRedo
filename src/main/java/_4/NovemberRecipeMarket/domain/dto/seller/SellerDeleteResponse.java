package _4.NovemberRecipeMarket.domain.dto.seller;

import lombok.Getter;

@Getter
public class SellerDeleteResponse {
    private Long id;
    private String message;

    public SellerDeleteResponse(Long id) {
        this.id = id;
        message = "%l을 삭제했습니다.";
    }
}
