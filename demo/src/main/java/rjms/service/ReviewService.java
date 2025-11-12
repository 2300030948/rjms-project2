package rjms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rjms.model.Review;
import rjms.repository.ReviewRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    public List<Review> getAllReviews() {
        return repository.findAll();
    }

    public Optional<Review> getReviewById(Long id) {
        return repository.findById(id);
    }

    public List<Review> getReviewsByPaper(Long paperId) {
        return repository.findByPaperId(paperId);
    }

    public Review saveReview(Review review) {
        return repository.save(review);
    }
}
