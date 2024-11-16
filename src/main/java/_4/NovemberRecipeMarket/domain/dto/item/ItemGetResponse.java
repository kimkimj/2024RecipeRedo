package _4.NovemberRecipeMarket.domain.dto.item;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemGetResponse {
    private Long itemId;
    private String itemName;

    private int price;

    private int stock;
}
