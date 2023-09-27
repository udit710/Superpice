package au.edu.rmit.sept.superprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import au.edu.rmit.sept.superprice.model.CartItem;
import au.edu.rmit.sept.superprice.repository.CartItemRepository;

import java.util.List;

@Service
public class CartItemService{
    
        private CartItemRepository cartItemRepository;
    
        @Autowired
        public CartItemService(CartItemRepository cartItemRepository) {
            this.cartItemRepository = cartItemRepository;    
        }
    
        public List<CartItem> getAllCartItems() {
            return cartItemRepository.findAll();
        }
    
        public CartItem getCartItemById(Long id) {
            return cartItemRepository.findById(id).orElse(null);
        }
    
        public CartItem saveOrUpdateCartItem(CartItem cartItem) {
            return cartItemRepository.save(cartItem);
        }
    
        public void deleteCartItem(Long id) {
            cartItemRepository.deleteById(id);
        }
    
        public List<CartItem> getCartItemsByUserId(Long userId) {
            return cartItemRepository.findByUserId(userId);
        }
    
        public List<CartItem> getCartItemsByProductDetailsId(Long productDetailsId) {
            return cartItemRepository.findByProductDetailsId(productDetailsId);
        }
    
        public List<CartItem> getCartItemsByQuantity(int quantity) {
            return cartItemRepository.findByQuantity(quantity);
        }
}