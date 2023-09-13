package au.edu.rmit.sept.superprice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import au.edu.rmit.sept.superprice.model.Product;
import au.edu.rmit.sept.superprice.service.ProductService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(
        List<Product> products = productService.getAllProducts();

        return products;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
