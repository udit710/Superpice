package au.edu.rmit.sept.superprice.Services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.ProductDetails;
import au.edu.rmit.sept.superprice.repository.ProductDetailRepository;
import au.edu.rmit.sept.superprice.service.ProductDetailService;

@SpringBootTest
public class ProductDetailServiceTest {
    
    ProductDetailRepository productDetailRepository;
    ProductDetailService productDetailService;

    @BeforeEach
    void init() {
        this.productDetailRepository = mock(ProductDetailRepository.class);
        this.productDetailService = new ProductDetailService(productDetailRepository);
    }

    @Test
    void should_get_details_by_id() {
        when(this.productDetailRepository.findById(1l))
        .thenReturn(Optional.of(new ProductDetails()));

        assertNotNull(this.productDetailService.getProductDetailsById(1l).get());
    }

    @Test
    void should_update_details() {
        ProductDetails productDetails = new ProductDetails();
        when(productDetailRepository.findById(1l))
        .thenReturn(Optional.of(productDetails));

        this.productDetailService.updateQuantity(1l, 5);
        verify(this.productDetailRepository, times(1))
        .save(productDetails);
    }
}
