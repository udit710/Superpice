package au.edu.rmit.sept.superprice.repository;

import au.edu.rmit.sept.superprice.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import au.edu.rmit.sept.superprice.model.User;
import au.edu.rmit.sept.superprice.model.ProductDetails;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query(value = "SELECT * FROM CART_ITEM", nativeQuery = true)
    List<CartItem> findAll();

    // @Query("SELECT c FROM CartItem c WHERE c.cartItemId = ?1")
    // CartItem findByCartItemId(Long cartItemId);

    @Query("SELECT c FROM CartItem c WHERE c.userId.userId = ?1")
    List<CartItem> findByUserId(Long userId);

    @Query("SELECT c FROM CartItem c WHERE c.productDetailsId.id = ?1")
    List<CartItem> findByProductDetailsId(Long productDetailsId);

    @Query("SELECT c FROM CartItem c WHERE c.quantity = ?1")
    List<CartItem> findByQuantity(int quantity);
}