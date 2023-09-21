package au.edu.rmit.sept.superprice.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Category;
import au.edu.rmit.sept.superprice.model.SubCategory;

@SpringBootTest
public class SubCategoryTest {
    
    SubCategory subCategory;

    @BeforeEach
    void createReview() {
        this.subCategory = new SubCategory(1l, "test", new Category());
    }

    @Test
    void should_return_id() {
        assertEquals(1l, this.subCategory.getSubCategoryId());
    }

    @Test
    void should_return_name() {
        assertEquals("test", this.subCategory.getSubCategoryName());
    }

    @Test
    void should_return_category() {
        assertNotNull(this.subCategory.getCategory());
    }

    // @Test
    // void should_return_products() {
    //     assertEquals(0, this.subCategory.getProductIds().size());
    // }

}
