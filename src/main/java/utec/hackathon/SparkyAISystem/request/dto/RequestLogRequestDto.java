package utec.hackathon.SparkyAISystem.request.dto;

import lombok.Data;

@Data
public class RequestLogRequestDto {
    private String model;
    private String prompt;
}