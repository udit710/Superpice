package au.edu.rmit.sept.superprice.Services;

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
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Review;
import au.edu.rmit.sept.superprice.repository.ReviewRepository;
import au.edu.rmit.sept.superprice.service.ReviewService;
import au.edu.rmit.sept.superprice.service.ReviewServiceImpl;

@SpringBootTest
public class ReviewServiceTest {
    
    ReviewService reviewService;
    ReviewRepository reviewRepository;

    @BeforeEach
    void init() {
        this.reviewRepository = mock(ReviewRepository.class);
        this.reviewService = new ReviewServiceImpl(this.reviewRepository);
    }

    @Test
    void should_return_all_reviews() {
        when(this.reviewRepository.findAll())
            .thenReturn(List.of(new Review()));
        
        assertEquals(1, this.reviewService.getAll().size());
    }

    @Test
    void should_return_empty_list_when_no_reviews_exist() {
        when(this.reviewRepository.findAll())
            .thenReturn(new ArrayList<Review>());
        
        assertEquals(0, this.reviewService.getAll().size());
    }

    @Test
    void should_return_review_by_id() {
        when(this.reviewRepository.findById(1l))
            .thenReturn(Optional.of(new Review()));
        
        assertNotNull(this.reviewService.getById(1l));
    }

    @Test
    void should_return_null_when_no_review_with_id_exists() {
        when(this.reviewRepository.findById(11l))
            .thenReturn(Optional.empty());
        
        assertNull(this.reviewService.getById(11l));
    }

    @Test
    void should_save_review() {
        Review review = new Review();
        when(this.reviewRepository.save(review))
            .then(returnsFirstArg());

        assertEquals(review, this.reviewService.save(review));
    }

    @Test
    void should_delete_review() {
        this.reviewService.deleteById(1l);

        verify(this.reviewRepository, times(1))
            .deleteById(1l);
    }

    @Test
    void should_return_reviews_by_productId() {
        when(this.reviewRepository.findByProductId(1l))
            .thenReturn(List.of(new Review()));
        
        assertEquals(1, this.reviewService.findByProductId(1l).size());
    }

    @Test
    void should_return_empty_list_when_no_reviews_exists_for_product() {
        when(this.reviewRepository.findByProductId(11l))
            .thenReturn(new ArrayList<Review>());
        
        assertEquals(0, this.reviewService.findByProductId(11l).size());
    }

}
