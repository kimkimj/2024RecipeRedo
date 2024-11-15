package _4.NovemberRecipeMarket.domain.dto.seller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class SellerUpdateRequest {
    private String username;
    private String companyName;
    private String phoneNumber;
    private String address;
    private String email;

}
