package au.edu.rmit.sept.superprice.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.CartItem;
import au.edu.rmit.sept.superprice.model.ProductDetails;
import au.edu.rmit.sept.superprice.model.User;

@SpringBootTest
public class CartItemTest {
    
    CartItem cartItem;

    @BeforeEach
    void createCartItem() {
        this.cartItem = new CartItem(1l, 1l, new ProductDetails(), new User(), 1);
    }

    @Test
    void should_return_id() {
        assertEquals(1l, this.cartItem.getId());
    }

    @Test
    void should_return_productDetails() {
        assertNotNull(this.cartItem.getProductDetailsId());
    }
    
    @Test
    void should_return_user() {
        assertNotNull(this.cartItem.getUserId());
    }

    @Test
    void should_return_qty() {
        assertEquals(1, this.cartItem.getQuantity());
    }

    @Test
    void should_return_productId() {
        assertEquals(1l, this.cartItem.getProductId());
    }

}
