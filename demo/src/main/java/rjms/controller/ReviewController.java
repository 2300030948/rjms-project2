package rjms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rjms.model.Review;
import rjms.service.ReviewService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @GetMapping
    public List<Review> getAllReviews() {
        return service.getAllReviews();
    }

    @GetMapping("/{id}")
    public Optional<Review> getReviewById(@PathVariable Long id) {
        return service.getReviewById(id);
    }

    @GetMapping("/paper/{paperId}")
    public List<Review> getReviewsByPaper(@PathVariable Long paperId) {
        return service.getReviewsByPaper(paperId);
    }

    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return service.saveReview(review);
    }
}
