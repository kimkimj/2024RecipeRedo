package _4.NovemberRecipeMarket.domain.dto.item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ItemRequest {
    @NotNull
    private String itemName;

    @Min(0)
    @NotNull
    private Integer price;

    @Min(0)
    @NotNull
    private Integer stock;
}
