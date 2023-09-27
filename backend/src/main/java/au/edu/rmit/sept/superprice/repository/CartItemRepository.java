package au.edu.rmit.sept.superprice.repository;

import au.edu.rmit.sept.superprice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query(value = "SELECT * FROM CART_ITEM", nativeQuery = true)
    List<CartItem> findAll();

    @Query("SELECT c FROM CartItem c WHERE c.cartItemId = ?1")
    CartItem findByCartItemId(Long cartItemId);

    @Query("SELECT c FROM CartItem c WHERE c.user = ?1")
    List<CartItem> findByUser(User user);

    @Query("SELECT c FROM CartItem c WHERE c.product = ?1")
    List<CartItem> findByProduct(ProductDetails product);

    @Query("SELECT c FROM CartItem c WHERE c.quantity = ?1")
    List<CartItem> findByQuantity(int quantity);
}