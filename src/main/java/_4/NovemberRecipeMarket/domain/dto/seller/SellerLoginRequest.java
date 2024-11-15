package _4.NovemberRecipeMarket.domain.dto.seller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SellerLoginRequest {
    private String username;
    private  String password;
}
