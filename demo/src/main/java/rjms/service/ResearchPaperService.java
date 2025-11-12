package rjms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rjms.model.ResearchPaper;
import rjms.repository.ResearchPaperRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ResearchPaperService {

    @Autowired
    private ResearchPaperRepository repository;

    public List<ResearchPaper> getAllPapers() {
        return repository.findAll();
    }

    public Optional<ResearchPaper> getPaperById(Long id) {
        return repository.findById(id);
    }

    public List<ResearchPaper> getPapersByAuthor(Long authorId) {
        return repository.findByAuthorId(authorId);
    }

    public ResearchPaper savePaper(ResearchPaper paper) {
        return repository.save(paper);
    }

    public void deletePaper(Long id) {
        repository.deleteById(id);
    }
}
