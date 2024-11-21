package _4.NovemberRecipeMarket.repository;

import _4.NovemberRecipeMarket.domain.entity.Recipe;
import _4.NovemberRecipeMarket.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Page<Recipe> findAllByAuthor(User author, Pageable pageable);
}
