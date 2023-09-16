package au.edu.rmit.sept.superprice.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Product;
import au.edu.rmit.sept.superprice.model.Review;
import au.edu.rmit.sept.superprice.service.ReviewService;
import au.edu.rmit.sept.superprice.web.ReviewController;

@SpringBootTest
public class ReviewControllerTest {
    
    ReviewController reviewController;
    ReviewService reviewService;

    @BeforeEach
    void initializeObjects() {
        this.reviewService = mock(ReviewService.class);
        this.reviewController = new ReviewController(this.reviewService);
    }

    @Test
    void should_return_all_reviews() {
        when(this.reviewService.getAll())
            .thenReturn(List.of(new Review(1l, 1l, new Product(), 5, "test review")));

        assertEquals(1, this.reviewController.getReviews(-1l).size());
    }

    @Test
    void should_return_empty_list_when_no_reviews() {
        when(this.reviewService.getAll()).thenReturn(new ArrayList<Review>());

        assertEquals(0, this.reviewController.getReviews(-1l).size());
    }

    @Test
    void should_return_all_reviews_for_a_single_product() {
        when(this.reviewService.findByProductId(1l))
            .thenReturn(List.of(
                new Review(1l, 1l, new Product(), 5, "test review 1"),
                new Review(2l, 3l, new Product(), 4, "test review 2")
            ));
        
        assertEquals(2, this.reviewController.getReviews(1l).size());
    }

    @Test
    void should_return_empty_list_when_no_reviews_for_product() {
        when(this.reviewService.findByProductId(1l))
            .thenReturn(new ArrayList<Review>());
        
        assertEquals(0, this.reviewController.getReviews(1l).size());
    }

    @Test
    void should_create_review() {
        Review testReview = new Review();
        // testReview.setUserId(1l);
        // testReview.setProductId(new Product());
        // testReview.setRating(5);
        // testReview.setComment("test review");

        when(this.reviewService.save(testReview)).then(returnsFirstArg());

        assertEquals(testReview, this.reviewController.createReview(testReview));
    }

    @Test
    void should_return_review_by_id() {
        when(this.reviewService.getById(1l))
            .thenReturn(new Review(1l, 1l, new Product(), 5, "test review 1"));
        
        assertNotNull(this.reviewController.getReviewById(1l));
    }

    @Test
    void should_return_null_when_review_id_doesNot_exist() {
        when(this.reviewService.getById(10l))
            .thenReturn(null);
        
        assertNull(this.reviewController.getReviewById(10l));
    }

    @Test
    void should_delete_review() {
        this.reviewController.deleteReview(1l);

        verify(this.reviewService, times(1))
            .deleteById(1l);

    }
}
