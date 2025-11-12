package rjms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rjms.model.Journal;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {
    // custom query methods if needed
}
