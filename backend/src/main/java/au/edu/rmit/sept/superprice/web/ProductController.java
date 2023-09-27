package au.edu.rmit.sept.superprice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import au.edu.rmit.sept.superprice.model.Product;
import au.edu.rmit.sept.superprice.service.ProductService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveOrUpdateProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        // Logic to update the product based on the provided ID
        return productService.saveOrUpdateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    //Add optionals below this
    // @GetMapping("/sub-category/id/{id}")
    // public List<Product> getProductsByProductCategoryId(@PathVariable Long id){
    //     return productService.getProductsBySubCategoryId(id);
    // }

    // @GetMapping("/sub-category/name/{name}")
    // public List<Product> getProductsByProductCategoryName(@PathVariable String name){
    //     return productService.getProductsBySubCategoryName(name);
    // }

    @GetMapping("/category/id/{id}")
    public List<Product> getProductsByCategoryId(@PathVariable Long id){
        return productService.getProductsByCategoryId(id);
    }

    @GetMapping("/category/name/{name}")
    public List<Product> getProductsByCategoryName(@PathVariable String name){
        return productService.getProductsByCategoryName(name);
    }

    @GetMapping("/offer/{discount}")
    public List<Product> getProductsByProductDetailsDiscount(@PathVariable Integer discount){
    return productService.getProductsByProductDetailsDiscount(discount);
    }

}
