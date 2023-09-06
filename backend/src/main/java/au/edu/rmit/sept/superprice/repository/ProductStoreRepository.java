package au.edu.rmit.sept.superprice.repository;

import au.edu.rmit.sept.superprice.model.ProductStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStoreRepository extends JpaRepository<ProductStore, Long> {
}
