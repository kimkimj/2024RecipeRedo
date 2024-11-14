package _4.NovemberRecipeMarket.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinResponse {
    private String username;
    private String message;

    public UserJoinResponse(String username) {
        this.username = username;
        message = username + "의  회원가입이 완료되었습니다.";
    }
}
