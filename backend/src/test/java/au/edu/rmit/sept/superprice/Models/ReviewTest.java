package au.edu.rmit.sept.superprice.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Product;
import au.edu.rmit.sept.superprice.model.Review;

@SpringBootTest
public class ReviewTest {
    
    Review review;

    @BeforeEach
    void createReview() {
        this.review = new Review(1l, 1l, new Product(), 5, "test review");
    }

    @Test
    void should_return_id() {
        assertEquals(1l, this.review.getReviewId());
    }

    @Test
    void should_return_userId() {
        assertEquals(1l, this.review.getUserId());
    }
    
    @Test
    void should_return_productId() {
        assertNotNull(this.review.getProductId());
    }

    @Test
    void should_return_rating() {
        assertEquals(5, this.review.getRating());
    }

    @Test
    void should_return_comment() {
        assertEquals("test review", this.review.getComment());
    }
    
}
