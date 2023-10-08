package au.edu.rmit.sept.superprice.repository;

import au.edu.rmit.sept.superprice.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetails, Long> {
    
}
