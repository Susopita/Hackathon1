package utec.hackathon.SparkyAISystem.request.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RequestLogResponseDto {
    private Long id;
    private String model;
    private String prompt;
    private String response;
    private int tokensConsumed;
    private double estimatedCost;
    private LocalDateTime timestamp;
    private Long userId;
}