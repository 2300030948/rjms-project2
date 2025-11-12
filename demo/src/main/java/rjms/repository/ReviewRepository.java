package rjms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rjms.model.Review;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Add this method to allow Spring Data JPA to implement it automatically
    List<Review> findByPaperId(Long paperId);

}
