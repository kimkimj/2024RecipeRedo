package _4.NovemberRecipeMarket.domain.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinRequest {
    private String username;
    private String password;
    private String name;

    private String birthdate;
    private String phoneNumber;

    private String email;
    private String address;
    
    
}
