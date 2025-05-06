package utec.hackathon.SparkyAISystem.Limit.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Duration;

@Entity
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelType;
    private int maxRequests;
    private int maxTokens;
    private Duration windowDuration;
}
