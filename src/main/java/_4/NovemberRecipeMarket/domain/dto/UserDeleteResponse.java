package _4.NovemberRecipeMarket.domain.dto;

public class UserDeleteResponse {
    private Long id;
    private String message;

    public UserDeleteResponse(Long id) {
        this.id = id;
        message = "회원 삭제가 완료되었습니다.";
    }
}
