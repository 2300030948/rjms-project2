package rjms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rjms.model.ResearchPaper;
import java.util.List;

@Repository
public interface ResearchPaperRepository extends JpaRepository<ResearchPaper, Long> {
    List<ResearchPaper> findByAuthorId(Long authorId);
}
