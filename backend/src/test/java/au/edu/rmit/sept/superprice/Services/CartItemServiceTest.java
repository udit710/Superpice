package au.edu.rmit.sept.superprice.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.CartItem;
import au.edu.rmit.sept.superprice.repository.CartItemRepository;
import au.edu.rmit.sept.superprice.service.CartItemService;
import au.edu.rmit.sept.superprice.model.ProductDetails;
import au.edu.rmit.sept.superprice.model.User;

@SpringBootTest
public class CartItemServiceTest {
    
    CartItemService cartItemService;
    CartItemRepository cartItemRepository;

    @BeforeEach
    void initializeObjects() {
        this.cartItemRepository = mock(CartItemRepository.class);
        this.cartItemService = new CartItemService(this.cartItemRepository);
    }

    @Test
    void should_return_all_cartItems() {
        User user = new User();
        ProductDetails productDetails = new ProductDetails();
        CartItem cartItem = new CartItem(1L, 1L, productDetails, user, 1);
        when(this.cartItemRepository.findAll())
        .thenReturn(List.of(cartItem));

        assertEquals(1, this.cartItemService.getAllCartItems().size());
    }

    @Test
    void should_return_cartItem_with_id_one() {
        User user = new User();
        ProductDetails productDetails = new ProductDetails();
        CartItem cartItem = new CartItem(1L, 1L, productDetails, user, 1);
        when(this.cartItemRepository.findById(1L)).thenReturn(Optional.of(cartItem));

        assertEquals(cartItem, this.cartItemService.getCartItemById(1L));
    }

    @Test
    void should_return_null_with_id_100() {
        when(this.cartItemRepository.findById(100L)).thenReturn(Optional.empty());

        assertNull(this.cartItemService.getCartItemById(100L));
    }

    @Test
    void should_delete_cartItem() {
        this.cartItemService.deleteCartItem(1l);

        verify(this.cartItemRepository, times(1))
            .deleteById(1l);
    }

    @Test
    void should_create_cartItem() {
        CartItem cartItem = new CartItem();
        this.cartItemService.saveOrUpdateCartItem(cartItem);

        verify(this.cartItemRepository, times(1))
            .save(cartItem);
    }
    
}
