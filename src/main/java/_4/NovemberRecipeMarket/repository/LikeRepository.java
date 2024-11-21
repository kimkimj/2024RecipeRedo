package _4.NovemberRecipeMarket.repository;

import _4.NovemberRecipeMarket.domain.entity.Like;
import _4.NovemberRecipeMarket.domain.entity.Recipe;
import _4.NovemberRecipeMarket.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByRecipeAndUser(Recipe recipe, User user);
    int countByRecipe(Recipe recipe);
}
