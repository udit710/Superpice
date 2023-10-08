package au.edu.rmit.sept.superprice.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.ProductDetails;
import au.edu.rmit.sept.superprice.service.ProductDetailService;
import au.edu.rmit.sept.superprice.web.ProductDetailController;

@SpringBootTest
public class ProductDetailControllerTest {
    
    ProductDetailController productDetailController;
    ProductDetailService productDetailService;
    
    @BeforeEach
    void initializeObjects() {
        this.productDetailService = mock(ProductDetailService.class);
        this.productDetailController = new ProductDetailController(this.productDetailService);
    }

    @Test
    void should_return_productDetails_by_id() {
        ProductDetails productDetails = new ProductDetails();
        when(this.productDetailService.getProductDetailsById(1l))
            .thenReturn(Optional.of(productDetails));
        
        assertEquals(productDetails, this.productDetailController.getProductDetailById(1l).get());
    }

}
