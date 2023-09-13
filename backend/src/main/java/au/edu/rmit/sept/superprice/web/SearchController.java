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

    @GetMapping("/name/{productName}")
    public List<Product> getProductByProductName(@PathVariable String productName) {
        return productService.getProductsByProductName(productName);
    }

    @GetMapping("/description/{description}")
    public List<Product> getProductsByDescription(@PathVariable String description) {
        return productService.getProductsByDescription(description);
    }

    @GetMapping("/lastUpdated/{lastUpdated}")
    public List<Product> getProductsByLastUpdated(@PathVariable String lastUpdated) {
        return productService.getProductsByLastUpdated(lastUpdated);
    }

    @GetMapping("/availability")
    public List<Product> getProductsByAvailability() {
        return productService.getProductsByAvailability();
    }

    @GetMapping("/price")
    public List<Product> getProductsByPriceBetween(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        return productService.getProductsByPriceBetween(minPrice, maxPrice);
    }

    @GetMapping("/allergens/{allergen}")
    public List<Product> getProductsByAllergens(@PathVariable String allergen) {
        return productService.getProductsByAllergens(allergen);
    }

    @GetMapping("/storeName/{storeName}")
    public List<Product> getProductsByStoreName(@PathVariable String storeName) {
        return productService.getProductsByStoreName(storeName);
    }

    @GetMapping("/storeIds")
    public List<Product> getProductsByStoreIds(@RequestParam List<Long> storeIds) {
        return productService.getProductsByStoreIds(storeIds);
    }
}
