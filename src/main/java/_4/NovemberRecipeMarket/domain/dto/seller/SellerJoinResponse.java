package _4.NovemberRecipeMarket.domain.dto.seller;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SellerJoinResponse {
    private Long id;
    private String companyName;
    private String message;

    public SellerJoinResponse(Long id, String companyName) {
        this.id = id;
        this.companyName = companyName;
        message = companyName + "의  회원가입이 완료되었습니다.";
    }
}
