package _4.NovemberRecipeMarket.domain.dto.seller;

import _4.NovemberRecipeMarket.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SellerResponse {
    private Long id;
    private String username;
    private String companyName;
    private String businessRegNum;
    private String phoneNumber;
    private String address;
    private String email;
    private String userRole;

}
