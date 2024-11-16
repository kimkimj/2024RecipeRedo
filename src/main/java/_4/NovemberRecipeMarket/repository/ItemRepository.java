package _4.NovemberRecipeMarket.repository;

import _4.NovemberRecipeMarket.domain.entity.Item;
import _4.NovemberRecipeMarket.domain.entity.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findById(Long itemId);
    Page<Item> findAllBySeller(Seller seller, Pageable pageable);
}
