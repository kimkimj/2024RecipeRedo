package _4.NovemberRecipeMarket.domain.dto.seller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class SellerJoinRequest {
    private String username;
    private String password;
    private String companyName;
    private String businessRegNum;
    private String phoneNumber;
    private String address;
    private String email;

}
