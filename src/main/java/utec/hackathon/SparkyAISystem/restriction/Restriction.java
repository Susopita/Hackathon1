package utec.hackathon.SparkyAISystem.restriction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Restriction {
    @Id
    @GeneratedValue
    private Long id;
    private String modelType; // Ej: "OPENAI_GPT4", "META_LLAMA"
    private int maxRequestsPerWindow; // Límite de solicitudes
    private int maxTokensPerWindow; // Límite de tokens
    private Duration windowDuration; // Ej: 24 horas

    @ManyToOne
    private Company company;
}
