package _4.NovemberRecipeMarket.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AppException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;

    public AppException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        message = errorCode.getMessage();
    }
}
