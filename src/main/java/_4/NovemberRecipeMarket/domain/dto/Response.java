package _4.NovemberRecipeMarket.domain.dto;

import _4.NovemberRecipeMarket.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {
    private String resultCode;
    private T result;

    public static <T> Response<T> success(T result) {
        return new Response<>("SUCCESS", result);
    }

    public static <T> Response<T> error(T result) {
        return new Response<>("ERROR", result);
    }

}
