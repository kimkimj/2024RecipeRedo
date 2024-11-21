package _4.NovemberRecipeMarket.repository;

import _4.NovemberRecipeMarket.domain.entity.RecipeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeItemRepository extends JpaRepository<RecipeItem, Long> {
}
