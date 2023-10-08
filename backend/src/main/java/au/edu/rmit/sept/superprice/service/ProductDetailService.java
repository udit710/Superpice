package au.edu.rmit.sept.superprice.service;

import au.edu.rmit.sept.superprice.model.ProductDetails;
import au.edu.rmit.sept.superprice.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductDetailService {

    private ProductDetailRepository productDetailRepository;

    @Autowired
    public ProductDetailService(ProductDetailRepository productDetailRepository){this.productDetailRepository = productDetailRepository;};

    public Optional<ProductDetails> getProductDetailsById(Long id){return productDetailRepository.findById(id);};

    public ProductDetails updateQuantity(Long id, Integer newQuantity) {
        // Retrieve the ProductDetails by ID
        ProductDetails productDetails = productDetailRepository.findById(id)
                .orElse(null);

        //Created the updated quantity value
        // int tempQuantity = productDetails.getAvailable() - newQuantity;

        // Update the quantity
        productDetails.setAvailable(newQuantity);

        // Save the updated ProductDetails
        return productDetailRepository.save(productDetails);
    }
}
