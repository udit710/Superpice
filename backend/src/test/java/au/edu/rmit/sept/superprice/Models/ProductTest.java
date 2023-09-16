package au.edu.rmit.sept.superprice.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Category;
import au.edu.rmit.sept.superprice.model.Product;
import au.edu.rmit.sept.superprice.model.ProductDetails;
import au.edu.rmit.sept.superprice.model.ProductImage;
import au.edu.rmit.sept.superprice.model.SubCategory;

@SpringBootTest
public class ProductTest {
    Product product;

    @BeforeEach
    void createReview() {
        this.product = new Product(1l, "test", "test", "test", "test", new Category(), new SubCategory(), List.of(new ProductImage()), List.of(new ProductDetails()));
    }

    @Test
    void should_return_id() {
        assertEquals(1l, this.product.getId());
    }

    @Test
    void should_return_productName() {
        assertEquals("test", this.product.getProductName());
    }
    
    @Test
    void should_return_desc() {
        assertEquals("test", this.product.getDescription());
    }

    @Test
    void should_return_allergens() {
        assertEquals("test", this.product.getAllergens());
    }

    @Test
    void should_return_lastUpdated() {
        assertEquals("test", this.product.getLastUpdated());
    }

    @Test
    void should_return_category() {
        assertNotNull(this.product.getCategory());
    }

    @Test
    void should_return_subcategory() {
        assertNotNull(this.product.getSubCategory());
    }

    @Test
    void should_return_images() {
        assertEquals(1, this.product.getImages().size());
    }

    @Test
    void should_return_details() {
        assertEquals(1, this.product.getDetails().size());
    }
    
}
