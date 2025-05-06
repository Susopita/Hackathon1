package utec.hackathon.SparkyAISystem.airequestlog;

import jakarta.persistence.*;
import org.apache.catalina.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ai_request_logs")
public class AiRequestLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prompt;

    @Column(columnDefinition = "TEXT")
    private String response;

    private int tokensUsed;

    private LocalDateTime timestamp;

    private String modelName;

    @ElementCollection
    @CollectionTable(name = "ai_request_files", joinColumns = @JoinColumn(name = "request_id"))
    @Column(name = "file_name")
    private List<String> fileNames;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    // --- Constructors ---
    public AiRequestLog() {
        this.timestamp = LocalDateTime.now();
    }

    public AiRequestLog(String prompt, String response, int tokensUsed, String modelName, List<String> fileNames, User user, Company company) {
        this.prompt = prompt;
        this.response = response;
        this.tokensUsed = tokensUsed;
        this.timestamp = LocalDateTime.now();
        this.modelName = modelName;
        this.fileNames = fileNames;
        this.user = user;
        this.company = company;
    }
}
