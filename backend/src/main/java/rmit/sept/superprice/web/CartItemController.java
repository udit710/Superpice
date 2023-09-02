package rmit.sept.superprice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rmit.sept.superprice.service.CartItemService;

@RestController
@RequestMapping("/cartitem")
public class CartItemController {

    @Autowired
    private CartItemService cartitemService;

    // TODO: Add controller methods
}
