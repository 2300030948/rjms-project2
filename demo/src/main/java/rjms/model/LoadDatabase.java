package rjms.model;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rjms.repository.JournalRepository;

import java.time.LocalDate;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(JournalRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Journal j1 = new Journal();
                j1.setTitle("AI Research Advances");
                j1.setAuthor("John Doe");
                j1.setPublishedDate(LocalDate.of(2025, 10, 1));
                j1.setContent("This is a test journal about AI research.");
                repository.save(j1);

                Journal j2 = new Journal();
                j2.setTitle("Quantum Computing Today");
                j2.setAuthor("Jane Smith");
                j2.setPublishedDate(LocalDate.of(2025, 9, 20));
                j2.setContent("Quantum computing developments in 2025.");
                repository.save(j2);

                System.out.println("Test journals loaded successfully!");
            } else {
                System.out.println("Database already initialized; skipping test data load.");
            }
        };
    }
}
