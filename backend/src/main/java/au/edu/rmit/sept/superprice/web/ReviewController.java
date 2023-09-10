package au.edu.rmit.sept.superprice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.edu.rmit.sept.superprice.model.Review;
import au.edu.rmit.sept.superprice.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAll();
    }
}
