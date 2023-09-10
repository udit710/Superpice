package au.edu.rmit.sept.superprice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import au.edu.rmit.sept.superprice.model.Review;
import au.edu.rmit.sept.superprice.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<Review> getReviews(@RequestParam(value = "product", defaultValue = "-1") Long productId) {
        if (productId == -1)
            return reviewService.getAll();
        
        return reviewService.findByProductId(productId);
    }
    
}
