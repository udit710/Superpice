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

    // @Query("SELECT o FROM Orders o WHERE o.status = ?1")
    // List<Order> findByStatus(Order.OrderStatus status);

    // @Query("SELECT o FROM Orders o WHERE o.lastUpdated = ?1")
    // List<Order> findByLastUpdated(String lastUpdated);

    // @Query("SELECT o FROM Orders o WHERE o.customerId = ?1 AND o.status = ?2")
    // List<Order> findByCustomerIdAndStatus(Long customerId, Order.OrderStatus status);

    // @Query("SELECT o FROM Orders o WHERE o.customerId = ?1 AND o.lastUpdated = ?2")
    // List<Order> findByCustomerIdAndLastUpdated(Long customerId, String lastUpdated);

    // @Query("SELECT o FROM Orders o WHERE o.status = ?1 AND o.lastUpdated = ?2")
    // List<Order> findByStatusAndLastUpdated(Order.OrderStatus status, String lastUpdated);

    // @Query("SELECT o FROM Orders o WHERE o.customerId = ?1 AND o.status = ?2 AND o.lastUpdated = ?3")
    // List<Order> findByCustomerIdAndStatusAndLastUpdated(Long customerId, Order.OrderStatus status, String lastUpdated);

    // @Query("SELECT o FROM Order o WHERE o.customerId = ?1 AND o.address.addressId = ?2")
    // List<Order> findByCustomerIdAndAddressId(Long customerId, Long addressId);
}
