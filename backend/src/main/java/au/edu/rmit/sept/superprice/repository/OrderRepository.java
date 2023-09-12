package au.edu.rmit.sept.superprice.repository;

import au.edu.rmit.sept.superprice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.userId = ?1")
    List<Order> findByUserId(Long userId);

    @Query("SELECT o FROM Order o WHERE o.orderStatus = ?1")
    List<Order> findByStatus(Order.OrderStatus orderStatus);
}
