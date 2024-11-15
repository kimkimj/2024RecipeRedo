package _4.NovemberRecipeMarket.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    // ID는 수정이 되지 않기 때문에 제외
    private String password;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private String birthDate;

}
