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

    private ProductService productService;

    @Autowired
    public SearchController(ProductService productService) {
        this.productService = productService;
    }
   

    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String lastUpdated,
            @RequestParam(required = false) List<Long> storeIds,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String allergen) {

        List<Product> products = productService.getAllProducts();

        if (name != null) {
            products = productService.getProductsByProductName(name);
        }

        if (description != null) {
            products = productService.getProductsByDescription(description);
        }

        if (lastUpdated != null) {
            products = productService.getProductsByLastUpdated(lastUpdated);
        }

        if (storeIds != null && !storeIds.isEmpty()) {
            products = productService.getProductsByStoreIds(storeIds);
        }

        if (minPrice != null && maxPrice != null) {
            products = productService.getProductsByPriceBetween(minPrice, maxPrice);
        }

        if (allergen != null) {
            products = productService.getProductsByAllergens(allergen);
        }

        return products;
    }

    // @GetMapping("/{id}")
    // public Product getProductById(@PathVariable Long id) {
    //     return productService.getProductById(id);
    // }

    // @GetMapping("/name/{productName}")
    // public List<Product> getProductByProductName(@PathVariable String productName) {
    //     return productService.getProductsByProductName(productName);
    // }

    // @GetMapping("/description/{description}")
    // public List<Product> getProductsByDescription(@PathVariable String description) {
    //     return productService.getProductsByDescription(description);
    // }

    // @GetMapping("/lastUpdated/{lastUpdated}")
    // public List<Product> getProductsByLastUpdated(@PathVariable String lastUpdated) {
    //     return productService.getProductsByLastUpdated(lastUpdated);
    // }

    // @GetMapping("/availability")
    // public List<Product> getProductsByAvailability() {
    //     return productService.getProductsByAvailability();
    // }

    // @GetMapping("/price")
    // public List<Product> getProductsByPriceBetween(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
    //     return productService.getProductsByPriceBetween(minPrice, maxPrice);
    // }

    // @GetMapping("/allergens/{allergen}")
    // public List<Product> getProductsByAllergens(@PathVariable String allergen) {
    //     return productService.getProductsByAllergens(allergen);
    // }

    // @GetMapping("/storeName/{storeName}")
    // public List<Product> getProductsByStoreName(@PathVariable String storeName) {
    //     return productService.getProductsByStoreName(storeName);
    // }

    // @GetMapping("/storeIds")
    // public List<Product> getProductsByStoreIds(@RequestParam List<Long> storeIds) {
    //     return productService.getProductsByStoreIds(storeIds);
    // }
}
