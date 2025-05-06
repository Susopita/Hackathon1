package utec.hackathon.SparkyAISystem.request.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utec.hackathon.SparkyAISystem.request.dto.RequestLogRequestDto;
import utec.hackathon.SparkyAISystem.request.dto.RequestLogResponseDto;
import java.util.List;

// Importa el servicio
import utec.hackathon.SparkyAISystem.request.domain.RequestLogService;

@RestController
@RequestMapping("/api/user/{userId}/requests")
@RequiredArgsConstructor
public class RequestLogController {
    private final RequestLogService service;

    @PostMapping
    public ResponseEntity<RequestLogResponseDto> create(
            @PathVariable Long userId,
            @RequestBody RequestLogRequestDto dto
    ) {
        return ResponseEntity.ok(service.log(userId, dto));
    }

    @GetMapping
    public ResponseEntity<List<RequestLogResponseDto>> list(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(service.findByUser(userId));
    }
}