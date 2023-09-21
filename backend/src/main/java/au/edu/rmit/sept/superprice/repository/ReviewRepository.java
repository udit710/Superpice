package au.edu.rmit.sept.superprice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import au.edu.rmit.sept.superprice.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    

    @Query("SELECT r FROM Review r WHERE r.productId.id = ?1")
    List<Review> findByProductId(Long productId);
    
}
