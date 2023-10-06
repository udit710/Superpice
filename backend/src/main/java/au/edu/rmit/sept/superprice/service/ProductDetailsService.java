package au.edu.rmit.sept.superprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.rmit.sept.superprice.model.ProductDetails;
import au.edu.rmit.sept.superprice.repository.ProductDetailsRepository;

@Service
public class ProductDetailsService {
    
    private ProductDetailsRepository productDetailsRepository;

    @Autowired
    public ProductDetailsService(ProductDetailsRepository productDetailsRepository) {
        this.productDetailsRepository = productDetailsRepository;
    }

    public ProductDetails getById(Long id) {
        return productDetailsRepository.findById(id).orElse(null);
    }
}
