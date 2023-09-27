package au.edu.rmit.sept.superprice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import au.edu.rmit.sept.superprice.model.Product;
import au.edu.rmit.sept.superprice.service.ProductService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
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
}
