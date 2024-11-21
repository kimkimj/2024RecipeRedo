package _4.NovemberRecipeMarket.domain.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartItemRequest {
    private Long itemId;
    private int quantity;
}
