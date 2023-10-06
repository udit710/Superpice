package au.edu.rmit.sept.superprice.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.CartItem;
import au.edu.rmit.sept.superprice.repository.CartItemRepository;
import au.edu.rmit.sept.superprice.service.CartItemService;
import au.edu.rmit.sept.superprice.web.CartItemController;
import au.edu.rmit.sept.superprice.model.ProductDetails;
import au.edu.rmit.sept.superprice.model.User;

@SpringBootTest
public class CartItemControllerTest {

    CartItemController cartItemController;
    CartItemService cartItemService;

    @BeforeEach
    void initializeObjects() {
        this.cartItemService = mock(CartItemService.class);
        this.cartItemController = new CartItemController(this.cartItemService);
    }

    @Test
    void should_return_all_cart_items() {
        CartItem cartItem = new CartItem();
        when(this.cartItemService.getAllCartItems())
            .thenReturn(List.of(cartItem));

        assertEquals(1, this.cartItemController.getAllCartItems().size());
    }

    @Test
    void should_return_empty_list_when_no_cart_items() {
        when(this.cartItemService.getAllCartItems()).thenReturn(new ArrayList<CartItem>());

        assertEquals(0, this.cartItemController.getAllCartItems().size());
    }

    @Test
    void should_return_all_cart_items_for_a_single_user() {
        User user = new User();
        ProductDetails productDetails = new ProductDetails();
        when(this.cartItemService.getCartItemsByUserId(4l))
            .thenReturn(List.of(
                new CartItem(4l, 4l, productDetails, user, 4),
                new CartItem(5l, 4l, productDetails, user, 4)
            ));

        assertEquals(2, this.cartItemController.getCartItemsByUserId(4l).size());
    }

    @Test
    void should_return_empty_list_when_no_cart_items_for_a_single_user() {
        when(this.cartItemService.getCartItemsByUserId(4l)).thenReturn(new ArrayList<CartItem>());

        assertEquals(0, this.cartItemController.getCartItemsByUserId(4l).size());
    }

    @Test
    void should_return_cart_item_by_id() {
        User user = new User();
        ProductDetails productDetails = new ProductDetails();
        CartItem cartItem = new CartItem(1L, 1L, productDetails, user, 1);
        when(this.cartItemService.getCartItemById(1L)).thenReturn(cartItem);

        assertEquals(1L, this.cartItemController.getCartItemById(1L).getId());
    }

    @Test
    void should_return_null_when_cart_item_not_found() {
        when(this.cartItemService.getCartItemById(100L)).thenReturn(null);

        assertNull(this.cartItemController.getCartItemById(100L));
    }

    @Test
    void should_delete_cart_item() {
        this.cartItemController.deleteCartItem(1l);

        verify(this.cartItemService, times(1))
            .deleteCartItem(1l);
    }

    @Test
    void should_update_cart_item() {
        CartItem cartItem = new CartItem();
        this.cartItemController.updateCartItem(1l, cartItem);

        verify(this.cartItemService, times(1))
            .saveOrUpdateCartItem(cartItem);
    }


}