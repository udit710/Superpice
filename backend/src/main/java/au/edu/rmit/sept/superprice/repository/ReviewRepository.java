package au.edu.rmit.sept.superprice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import au.edu.rmit.sept.superprice.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
}
