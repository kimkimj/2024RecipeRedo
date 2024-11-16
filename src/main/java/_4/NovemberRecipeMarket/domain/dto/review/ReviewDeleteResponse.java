package _4.NovemberRecipeMarket.domain.dto.review;

import lombok.Getter;

@Getter
public class ReviewDeleteResponse {
    private Long reviewId;
    private String message;

    public ReviewDeleteResponse(Long reviewId) {
        this.reviewId = reviewId;
        message = "리뷰가 삭제되었습니다.";
    }
}