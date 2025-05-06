package utec.hackathon.SparkyAISystem.user.dto;

import lombok.Data;
import java.util.List;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private String name;
    private boolean active;
    private Long companyId;
    private List<Long> requestIds;
    private List<Long> limitIds;
}