package au.edu.rmit.sept.superprice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import au.edu.rmit.sept.superprice.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    // TODO: Change once user model exists
    @Query("SELECT r FROM Review r WHERE r.userId = ?1")
    Optional<Review> findByUserId(Long userId);

    // @Query("SELECT r FROM Review r WHERE r.userId = ?1")
    // Optional<Review> findByUserId(Long userId);
    
}
