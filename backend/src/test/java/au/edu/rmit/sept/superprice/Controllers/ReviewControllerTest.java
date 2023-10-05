package au.edu.rmit.sept.superprice.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import au.edu.rmit.sept.superprice.model.Product;
import au.edu.rmit.sept.superprice.model.Review;
import au.edu.rmit.sept.superprice.service.ReviewService;
import au.edu.rmit.sept.superprice.web.ProductController;
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

    // @Test
    // void should_create_review() throws JsonMappingException, JsonProcessingException {
    //     // String object = "{ \"comment\": \"test\"}";
    //     // ObjectMapper mapper = new ObjectMapper();
    //     // this.reviewController.updateReview(1l, mapper.readTree(object).deepCopy());
    //     // // this.reviewController.createReview(null);

    //     // verify(this.reviewService, times(1))
    //     //     .save(null);
    //     // String object = "{\"productId\":1, \"userId\":1, \"rating\":1, \"comment\":\"test\"}";
    //     // ObjectMapper mapper = new ObjectMapper();
    //     // ProductController productController = mock(ProductController.class);
    //     // when(productController.getProductById(1l)).thenReturn(new Product());
    //     // when(this.reviewService.save(any(Review.class))).thenReturn(new Review());

    //     // assertNotNull(this.reviewController.createReview(mapper.readTree(object).deepCopy()));
    // }

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

    @Test
    void should_update_reviews() throws JsonMappingException, JsonProcessingException {
        String object = "{ \"comment\": \"test\"}";
        ObjectMapper mapper = new ObjectMapper();
        Review review = new Review();
        when(this.reviewService.getById(1l)).thenReturn(review);
        when (this.reviewService.save(review)).thenReturn(review);

        assertEquals(review, this.reviewController.updateReview(1l, mapper.readTree(object).deepCopy()));
    }
}
