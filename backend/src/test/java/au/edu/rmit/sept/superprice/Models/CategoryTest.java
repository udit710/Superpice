package au.edu.rmit.sept.superprice.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Category;
import au.edu.rmit.sept.superprice.model.Product;
import au.edu.rmit.sept.superprice.model.SubCategory;

@SpringBootTest
public class CategoryTest {
    
    Category category;

    @BeforeEach
    void createCategory() {
        this.category = new Category(1l, "test", new ArrayList<SubCategory>(), new ArrayList<Product>());
    }

    @Test
    void should_return_id() {
        assertEquals(1l, this.category.getCategoryId());
    }

    @Test
    void should_return_CategoryName() {
        assertEquals("test", this.category.getCategoryName());
    }

    @Test
    void should_return_subCategories() {
        assertEquals(0, this.category.getSubCategories().size());
    }

    @Test
    void should_return_products() {
        assertEquals(0, this.category.getProducts().size());
    }

}
