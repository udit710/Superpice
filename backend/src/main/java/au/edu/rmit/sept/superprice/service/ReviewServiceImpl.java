package au.edu.rmit.sept.superprice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.rmit.sept.superprice.model.Review;
import au.edu.rmit.sept.superprice.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService{
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public List<Review> findByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    // public Review findByUserId(Long userId) {
    //     return reviewRepository.findByUserId(userId).orElse(null);
    // }
}
