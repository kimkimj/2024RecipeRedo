package _4.NovemberRecipeMarket.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    DUPLICATE_USERNAME(HttpStatus.CONFLICT, "사용자의 아이디가 중복됩니다"),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "중복된 이메일 입니다"),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 아이디입니다"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 다릅니다"),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "사용자가 권한이 없습니다."),
    DUPLICATE_BUSINESS_REG_NUM(HttpStatus.CONFLICT, "이미 등록된 사업자 등록 번호입니다."),
    INVALID_TOKEN(HttpStatus.NOT_FOUND, "만료된 토큰입니다."),
    ROLE_FORBIDDEN(HttpStatus.FORBIDDEN, "허용되지 않은 권한입니다."),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "DB 에러입니다"),
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 리뷰입니다.");


    private HttpStatus httpStatus;
    private String message;
}
