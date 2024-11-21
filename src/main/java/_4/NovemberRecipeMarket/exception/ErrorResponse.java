package _4.NovemberRecipeMarket.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private ErrorCode errorCode;
    private String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.errorCode = errorCode;
        message =errorCode.getMessage();
    }
}