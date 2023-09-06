package au.edu.rmit.sept.superprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import au.edu.rmit.sept.superprice.repository.CartItemRepository;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartitemRepository;

    // TODO: Add service methods
}
