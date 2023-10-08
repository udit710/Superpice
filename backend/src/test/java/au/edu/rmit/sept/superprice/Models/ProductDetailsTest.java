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
        this.productDetails = new ProductDetails(1l, null, null, 1.0, 1, 1.0, 1);
    }

    @Test
    void should_return_id() {
        this.productDetails.setId(2l);
        assertEquals(2l, this.productDetails.getId());
    }

    @Test
    void should_return_product() {
        this.productDetails.setProduct(new Product());
        assertNotNull(this.productDetails.getProduct());
    }

    @Test
    void should_return_Store() {
        this.productDetails.setStore(new Store());
        assertNotNull(this.productDetails.getStore());
    }

    @Test
    void should_return_Original_price() {
        this.productDetails.setOriginal_price(2.0);
        assertEquals(2.0, this.productDetails.getOriginal_price());
    }

    @Test
    void should_return_Discount() {
        this.productDetails.setDiscount(2);
        assertEquals(2, this.productDetails.getDiscount());
    }

    @Test
    void should_return_Price() {
        this.productDetails.setPrice(2d);
        assertEquals(2d, this.productDetails.getPrice());
    }

    @Test
    void should_return_Available() {
        this.productDetails.setAvailable(2);
        assertEquals(2, this.productDetails.getAvailable());
    }

}
