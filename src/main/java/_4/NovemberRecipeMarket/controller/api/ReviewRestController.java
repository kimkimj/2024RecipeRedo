package _4.NovemberRecipeMarket.controller.api;

import _4.NovemberRecipeMarket.domain.dto.Response;
import _4.NovemberRecipeMarket.domain.dto.review.ReviewDeleteResponse;
import _4.NovemberRecipeMarket.domain.dto.review.ReviewRequest;
import _4.NovemberRecipeMarket.domain.dto.review.ReviewResponse;
import _4.NovemberRecipeMarket.domain.dto.review.ReviewResponseForList;
import _4.NovemberRecipeMarket.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewRestController {

    private final ReviewService reviewService;

    @PostMapping()
    public Response<ReviewResponse> createReview(Authentication authentication,
                                                 @RequestBody ReviewRequest request) {
        ReviewResponse reviewResponse = reviewService.createReview(authentication.getName(), request);
        return Response.success(reviewResponse);
    }

    @PutMapping("/{reviewId}")
    public Response<ReviewResponse> updateReview(@PathVariable Long reviewId,
                                                 Authentication authentication,
                                                 @RequestBody ReviewRequest request) {
        ReviewResponse reviewResponse = reviewService.updateReview(reviewId, authentication.getName(), request);
        return Response.success(reviewResponse);
    }

    @DeleteMapping("/{reviewId}")
    public Response<ReviewDeleteResponse> deleteReview(@PathVariable Long reviewId,
                                                       Authentication authentication) {
        ReviewDeleteResponse reviewDeleteResponse = reviewService.deleteReview(reviewId, authentication.getName());
        return Response.success(reviewDeleteResponse);
    }

    @GetMapping("/{userId}")
    public Response<Page<ReviewResponseForList>> getAllReviewsByUser(@PathVariable Long userId, Authentication authentication,
                                                                     Pageable pageable) {
        Page<ReviewResponseForList> allReviewsByUser = reviewService.getAllReviewsByUser(userId, authentication.getName(), pageable);
        return Response.success(allReviewsByUser);
    }
}
