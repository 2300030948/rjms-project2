package rjms.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "research_papers")
public class ResearchPaper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String abstractText; // avoid using 'abstract' keyword
    private String fileUrl; // path or URL to uploaded file
    private String status; // SUBMITTED, UNDER_REVIEW, ACCEPTED, REJECTED

    private Long authorId; // User ID of author

    private LocalDateTime submittedAt = LocalDateTime.now();

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAbstractText() { return abstractText; }
    public void setAbstractText(String abstractText) { this.abstractText = abstractText; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }

    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
}
