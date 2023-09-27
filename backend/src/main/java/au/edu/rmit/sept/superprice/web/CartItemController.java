package au.edu.rmit.sept.superprice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import au.edu.rmit.sept.superprice.model.CartItem;
import au.edu.rmit.sept.superprice.service.CartItemService;
import au.edu.rmit.sept.superprice.model.User;
import au.edu.rmit.sept.superprice.model.ProductDetails;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/cartItems")
public class CartItemController {

    private final CartItemService cartItemService;

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
    public CartItem createCartItem(@RequestBody CartItem cartItem) {
        return cartItemService.saveOrUpdateCartItem(cartItem);
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