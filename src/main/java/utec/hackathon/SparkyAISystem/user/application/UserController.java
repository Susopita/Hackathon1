package utec.hackathon.SparkyAISystem.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utec.hackathon.SparkyAISystem.user.application.UserService;
import utec.hackathon.SparkyAISystem.user.domain.User;
import java.util.List;

@RestController
@RequestMapping("/api/company/{companyId}/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping
    public ResponseEntity<User> create(
            @PathVariable Long companyId,
            @RequestBody User user
    ) {
        return ResponseEntity.ok(service.create(user, companyId));
    }

    @GetMapping
    public ResponseEntity<List<User>> list(@PathVariable Long companyId) {
        return ResponseEntity.ok(service.findAllByCompany(companyId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(
            @PathVariable Long companyId,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(
            @PathVariable Long id,
            @RequestBody User user
    ) {
        return ResponseEntity.ok(service.update(id, user));
    }
}