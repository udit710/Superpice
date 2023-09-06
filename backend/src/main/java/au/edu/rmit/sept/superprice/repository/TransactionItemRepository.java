package au.edu.rmit.sept.superprice.repository;

import au.edu.rmit.sept.superprice.model.TransactionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionItemRepository extends JpaRepository<TransactionItem, Long> {
}
