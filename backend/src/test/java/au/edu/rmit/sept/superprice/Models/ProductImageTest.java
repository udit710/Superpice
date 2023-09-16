package au.edu.rmit.sept.superprice.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Product;
import au.edu.rmit.sept.superprice.model.ProductImage;

@SpringBootTest
public class ProductImageTest {

    ProductImage productImage;

    @BeforeEach
    void createReview() {
        this.productImage = new ProductImage(1l, new Product(), "test");
    }

    @Test
    void should_return_ImageId() {
        assertEquals(1l, this.productImage.getImageId());
    }

    @Test
    void should_return_ImageUrl() {
        assertEquals("test", this.productImage.getImageUrl());
    }

    @Test
    void should_return_Product() {
        assertNotNull(this.productImage.getProduct());
    }
}
