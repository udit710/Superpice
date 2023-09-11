package au.edu.rmit.sept.superprice.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import au.edu.rmit.sept.superprice.model.Review;
import au.edu.rmit.sept.superprice.service.ReviewService;
import au.edu.rmit.sept.superprice.web.ReviewController;

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
            .thenReturn(List.of(new Review(1l, 1, 1, 5, "test review")));

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
                new Review(1l, 1, 1, 5, "test review 1"),
                new Review(2l, 3, 1, 4, "test review 2")
            ));
        
        assertEquals(2, this.reviewController.getReviews(1l).size());
    }

    @Test
    void should_return_empty_list_when_no_reviews_for_product() {
        when(this.reviewService.findByProductId(1l))
            .thenReturn(new ArrayList<Review>());
        
        assertEquals(0, this.reviewController.getReviews(1l).size());
    }
}
