package au.edu.rmit.sept.superprice.service;

import java.util.List;

import au.edu.rmit.sept.superprice.model.Review;

public interface ReviewService {

    public List<Review> getAll();

    public Review getById(Long id);

    public Review save(Review review);

    public void deleteById(Long id);

    public List<Review> findByProductId(Long productId);
}
