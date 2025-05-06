package utec.hackathon.SparkyAISystem.request.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utec.hackathon.SparkyAISystem.request.domain.RequestLog;
import utec.hackathon.SparkyAISystem.request.domain.RequestLogService;
import utec.hackathon.SparkyAISystem.user.infrastructure.UserRepository;
import utec.hackathon.SparkyAISystem.user.domain.User;
import java.util.List;

@RestController
@RequestMapping("/api/user/{userId}/requests")
@RequiredArgsConstructor
public class RequestLogController {
    private final RequestLogService service;
    private final UserRepository userRepo;

    @PostMapping
    public ResponseEntity<RequestLog> create(
            @PathVariable Long userId,
            @RequestBody RequestLog log
    ) {
        User u = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        log.setUser(u);
        return ResponseEntity.ok(service.log(log));
    }

    @GetMapping
    public ResponseEntity<List<RequestLog>> list(@PathVariable Long userId) {
        return ResponseEntity.ok(service.findByUser(userId));
    }
}