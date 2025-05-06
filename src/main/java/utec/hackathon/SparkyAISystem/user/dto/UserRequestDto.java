package utec.hackathon.SparkyAISystem.user.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String email;
    private String password;
    private String name;
    private boolean active;
}