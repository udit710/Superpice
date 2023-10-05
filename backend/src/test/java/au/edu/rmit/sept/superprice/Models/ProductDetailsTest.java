package au.edu.rmit.sept.superprice.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Product;
import au.edu.rmit.sept.superprice.model.ProductDetails;
import au.edu.rmit.sept.superprice.model.Store;

@SpringBootTest
public class ProductDetailsTest {
    
    ProductDetails productDetails;

    @BeforeEach
    void createProductDetails() {
        this.productDetails = new ProductDetails(1l, new Product(), new Store(), 1.0, 1, 1.0, 1);
    }

    @Test
    void should_return_id() {
        assertEquals(1l, this.productDetails.getId());
    }

    @Test
    void should_return_product() {
        assertNotNull(this.productDetails.getProduct());
    }

    @Test
    void should_return_Store() {
        assertNotNull(this.productDetails.getStore());
    }

    @Test
    void should_return_Original_price() {
        assertEquals(1.0, this.productDetails.getOriginal_price());
    }

    @Test
    void should_return_Discount() {
        assertEquals(1, this.productDetails.getDiscount());
    }

    @Test
    void should_return_Price() {
        assertEquals(1.0, this.productDetails.getPrice());
    }

    @Test
    void should_return_Available() {
        assertEquals(1, this.productDetails.getAvailable());
    }

}
