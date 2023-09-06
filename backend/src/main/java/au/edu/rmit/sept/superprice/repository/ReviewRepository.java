package au.edu.rmit.sept.superprice.repository;

import au.edu.rmit.sept.superprice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
