package au.edu.rmit.sept.superprice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.node.ObjectNode;

import au.edu.rmit.sept.superprice.model.CartItem;
import au.edu.rmit.sept.superprice.service.CartItemService;
import au.edu.rmit.sept.superprice.service.ProductDetailsService;
import au.edu.rmit.sept.superprice.model.User;
import au.edu.rmit.sept.superprice.model.ProductDetails;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cartItems")
public class CartItemController {

    private final CartItemService cartItemService;

    @Autowired
    UserController userController;
    @Autowired
    ProductDetailsService productDetailsService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public List<CartItem> getAllCartItems() {
        return cartItemService.getAllCartItems();
    }

    @GetMapping("/{id}")
    public CartItem getCartItemById(@PathVariable Long id) {
        return cartItemService.getCartItemById(id);
    }

    @PostMapping
    public CartItem createCartItem(@RequestBody ObjectNode cartItem) {
        User user = userController.getUserById(cartItem.get("userId").asLong());
        ProductDetails productDetails = productDetailsService.getById(cartItem.get("productDetailsId").asLong());

        CartItem cart = new CartItem(null, cartItem.get("productId").asLong(), productDetails, user, cartItem.get("quantity").asInt());
        return cartItemService.saveOrUpdateCartItem(cart);
    }

    @PutMapping("/{id}")
    public CartItem updateCartItem(@PathVariable Long id, @RequestBody CartItem cartItem) {
        // Logic to update the cartItem based on the provided ID
        return cartItemService.saveOrUpdateCartItem(cartItem);
    }

    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
    }

    @GetMapping("/userId/{userId}")
    public List<CartItem> getCartItemsByUserId(@PathVariable Long userId) {
        return cartItemService.getCartItemsByUserId(userId);
    }

    @GetMapping("/productDetailsId/{productDetailsId}")
    public List<CartItem> getCartItemsByProductDetailsId(@PathVariable Long productDetailsId) {
        return cartItemService.getCartItemsByProductDetailsId(productDetailsId);
    }

    @GetMapping("/quantity/{quantity}")
    public List<CartItem> getCartItemsByQuantity(@PathVariable int quantity) {
        return cartItemService.getCartItemsByQuantity(quantity);
    }
}