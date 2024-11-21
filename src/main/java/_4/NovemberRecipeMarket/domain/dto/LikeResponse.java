package _4.NovemberRecipeMarket.domain.dto;

import lombok.Getter;

@Getter
public class LikeResponse {
    private Long likeId;
    private String message;

    public LikeResponse(Long likeId, String message) {
        this.likeId = likeId;
        this.message = message;
    }
}
