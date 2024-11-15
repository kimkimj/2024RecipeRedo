package _4.NovemberRecipeMarket.repository;

import _4.NovemberRecipeMarket.domain.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findByUsername(String username);
    Optional<Seller> findById(Long id);

    Optional<Seller> findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByBusinessRegNum(String businessRegNum);
}
