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
        this.review = new Review(1l, 1l, null, 5, "test review");
    }

    @Test
    void should_return_id() {
        assertEquals(1l, this.review.getReviewId());
    }

    @Test
    void should_return_userId() {
        this.review.setUserId(2l);
        assertEquals(2l, this.review.getUserId());
    }
    
    @Test
    void should_return_productId() {
        this.review.setProductId(new Product());
        assertNotNull(this.review.getProductId());
    }

    @Test
    void should_return_rating() {
        this.review.setRating(2);
        assertEquals(2, this.review.getRating());
    }

    @Test
    void should_return_comment() {
        this.review.setComment("testing");
        assertEquals("testing", this.review.getComment());
    }
    
}
