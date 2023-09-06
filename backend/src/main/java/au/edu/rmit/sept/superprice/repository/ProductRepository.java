package au.edu.rmit.sept.superprice.repository;

import au.edu.rmit.sept.superprice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.productName = ?1")
    Optional<Product> findByProductName(String productName);

    @Query("SELECT p FROM Product p WHERE p.description LIKE %?1%")
    List<Product> findByDescriptionContaining(String description);

    @Query("SELECT p FROM Product p WHERE p.lastUpdated = ?1")
    List<Product> findByLastUpdated(String lastUpdated);

    // @Query("SELECT p FROM Product p WHERE p.availability = ?1")
    // List<Product> findByAvailability(Boolean availability);

    // @Query("SELECT p FROM Product p WHERE p.price BETWEEN ?1 AND ?2")
    // List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

}
