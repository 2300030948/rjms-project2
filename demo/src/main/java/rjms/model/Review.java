package rjms.model;

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long paperId; // this must match the method name in the repository

    private String comments;

    // getters and setters
}
