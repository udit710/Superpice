package au.edu.rmit.sept.superprice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import au.edu.rmit.sept.superprice.model.Product;
import au.edu.rmit.sept.superprice.model.Review;
import au.edu.rmit.sept.superprice.service.ReviewService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/reviews")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Autowired
    public ProductController productController;

    @GetMapping
    public List<Review> getReviews(@RequestParam(value = "product", defaultValue = "-1") Long productId) {
        if (productId == -1)
            return reviewService.getAll();

        return reviewService.findByProductId(productId);
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id) {
        return reviewService.getById(id);
    }

    @PostMapping
    public Review createReview(@RequestBody ObjectNode objectNode) {
        Product product = productController.getProductById(objectNode.get("productId").asLong());
        Review review = new Review();
        review.setUserId(objectNode.get("userId").asLong());
        review.setProductId(product);
        review.setRating(objectNode.get("rating").asInt());
        review.setComment(objectNode.get("comment").asText());
        return reviewService.save(review);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Review updateReview(@PathVariable Long id, @RequestBody ObjectNode objectNode) {
        Review review = reviewService.getById(id);
        review.setComment(objectNode.get("comment").asText());
        return reviewService.save(review);
    }

}
