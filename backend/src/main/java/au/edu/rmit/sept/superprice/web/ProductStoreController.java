package au.edu.rmit.sept.superprice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import au.edu.rmit.sept.superprice.service.ProductStoreService;

@RestController
@RequestMapping("/productstore")
public class ProductStoreController {

    @Autowired
    private ProductStoreService productstoreService;

    // TODO: Add controller methods
}
