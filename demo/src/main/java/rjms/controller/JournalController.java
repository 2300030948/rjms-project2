package rjms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rjms.model.Journal;
import rjms.repository.JournalRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/journals")
public class JournalController {

    @Autowired
    private JournalRepository journalRepository;

    // ✅ Get all journals
    @GetMapping
    public List<Journal> getAllJournals() {
        return journalRepository.findAll();
    }

    // ✅ Get single journal by ID
    @GetMapping("/{id}")
    public ResponseEntity<Journal> getJournalById(@PathVariable Long id) {
        Journal journal = journalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Journal not found with id: " + id));
        return ResponseEntity.ok(journal);
    }

    // ✅ Create new journal
    @PostMapping
    public ResponseEntity<Journal> createJournal(@RequestBody Journal journal) {
        Journal savedJournal = journalRepository.save(journal);
        return ResponseEntity.ok(savedJournal);
    }

    // ✅ Update existing journal
    @PutMapping("/{id}")
    public ResponseEntity<Journal> updateJournal(@PathVariable Long id, @RequestBody Journal journalDetails) {
        Journal journal = journalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Journal not found with id: " + id));

        journal.setTitle(journalDetails.getTitle());
        journal.setAuthor(journalDetails.getAuthor());
        journal.setPublishedDate(journalDetails.getPublishedDate());
        journal.setContent(journalDetails.getContent()); // ✅ ensure "content" exists in your Journal model

        Journal updated = journalRepository.save(journal);
        return ResponseEntity.ok(updated);
    }

    // ✅ Delete journal
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteJournal(@PathVariable Long id) {
        Journal journal = journalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Journal not found with id: " + id));

        journalRepository.delete(journal);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
