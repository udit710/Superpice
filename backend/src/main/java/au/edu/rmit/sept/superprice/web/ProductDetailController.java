package au.edu.rmit.sept.superprice.web;


import au.edu.rmit.sept.superprice.model.ProductDetails;
import au.edu.rmit.sept.superprice.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/product-detail")
public class ProductDetailController {

    private final ProductDetailService productDetailService;

    @Autowired
    public ProductDetailController(ProductDetailService productDetailService){this.productDetailService = productDetailService;};

    @GetMapping("/{id}")
    public Optional<ProductDetails> getProductDetailById(@PathVariable Long id){return productDetailService.getProductDetailsById(id);};

    @PutMapping("/{id}/update-quantity")
    public ResponseEntity<ProductDetails> updateQuantity(
            @PathVariable Long id,
            @RequestParam Integer newQuantity) {

        ProductDetails updatedProductDetails = productDetailService.updateQuantity(id, newQuantity);
        return new ResponseEntity<>(updatedProductDetails, HttpStatus.OK);
    }
}

