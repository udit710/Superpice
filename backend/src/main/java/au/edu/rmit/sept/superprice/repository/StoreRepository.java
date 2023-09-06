package au.edu.rmit.sept.superprice.repository;

import au.edu.rmit.sept.superprice.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}
