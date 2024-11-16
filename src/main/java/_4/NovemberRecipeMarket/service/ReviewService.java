package _4.NovemberRecipeMarket.service;

import _4.NovemberRecipeMarket.domain.UserRole;
import _4.NovemberRecipeMarket.domain.dto.review.ReviewDeleteResponse;
import _4.NovemberRecipeMarket.domain.dto.review.ReviewRequest;
import _4.NovemberRecipeMarket.domain.dto.review.ReviewResponse;
import _4.NovemberRecipeMarket.domain.dto.review.ReviewResponseForList;
import _4.NovemberRecipeMarket.domain.entity.Review;
import _4.NovemberRecipeMarket.domain.entity.User;
import _4.NovemberRecipeMarket.exception.AppException;
import _4.NovemberRecipeMarket.exception.ErrorCode;
import _4.NovemberRecipeMarket.repository.ReviewRepository;
import _4.NovemberRecipeMarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public ReviewResponse createReview(String username, ReviewRequest request) {
        User author = validateUserByUsername(username);
        Review review = new Review(author, request.getTitle(), request.getContent());
        reviewRepository.save(review);
        return new ReviewResponse(review.getId(), username, review.getTitle(), review.getContent(),
                "리뷰가 등록되었습니다.");
    }

    public ReviewResponse updateReview(Long reviewId, String username, ReviewRequest request){
        User author = validateUserByUsername(username);
        Review review = checkIfAuthor(reviewId, author);
        review.updateReview(request.getTitle(), request.getContent());
        return new ReviewResponse(review.getId(), review.getAuthor().getUsername(), review.getTitle(),
                review.getContent(), "리뷰가 수정되었습니다.");
    }

    public ReviewDeleteResponse deleteReview(Long reviewId, String username) {
        User author = validateUserByUsername(username);
        Review review = checkIfAuthor(reviewId, author);

        reviewRepository.delete(review);
        return new ReviewDeleteResponse(reviewId);
    }

    @Transactional(readOnly = true)
    public Page<ReviewResponseForList> getAllReviewsByUser(Long userId, String username, Pageable pageable) {
        User author = checkForPermission(userId, username);
        Page<Review> reviews = reviewRepository.findAllByAuthor(author, pageable);
        return reviews.map(this::toReviewResponse);
    }

    private ReviewResponseForList toReviewResponse(Review review) {
        return new ReviewResponseForList(
                review.getId(),
                review.getAuthor().getUsername(),
                review.getTitle(),
                review.getContent());
    }

    private Review checkIfAuthor(Long reviewId, User user) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new AppException(ErrorCode.REVIEW_NOT_FOUND));

        String author = review.getAuthor().getUsername();
        if (!author.equals(user.getUsername()) && !isAdmin(user)) {
            throw new AppException(ErrorCode.INVALID_PERMISSION);
        }
        return review;
    }

    private boolean isAdmin(User user) {
        return user.getUserRole() == UserRole.ADMIN;
    }

    private User checkForPermission(Long userId, String username) {
        User user = validateUserByUsername(username);
        if (!user.getId().equals(userId)) {
            throw new AppException(ErrorCode.INVALID_PERMISSION);
        }
        return user;
    }

    private User validateUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));
        return user;
    }
}
