package _4.NovemberRecipeMarket.repository;

import _4.NovemberRecipeMarket.domain.entity.Recipe;
import _4.NovemberRecipeMarket.domain.entity.Review;
import _4.NovemberRecipeMarket.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findById(Long reviewId);
    Page<Review> findAllByAuthor(User user, Pageable pageable);
    Page<Review> findAllByRecipe(Recipe recipe, Pageable pageable);


}
