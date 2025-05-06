package utec.hackathon.SparkyAISystem.user.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utec.hackathon.SparkyAISystem.user.domain.Role;
import utec.hackathon.SparkyAISystem.user.domain.User;
import utec.hackathon.SparkyAISystem.user.domain.UserService;
import utec.hackathon.SparkyAISystem.user.dto.UserRequestDto;
import utec.hackathon.SparkyAISystem.user.dto.UserResponseDto;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/company/{companyId}/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(
            @PathVariable Long companyId,
            @RequestBody UserRequestDto dto
    ) {
        // Inicializar usuario con valores DTO
        User u = new User();
        u.setEmail(dto.getEmail());
        u.setPassword(dto.getPassword());
        u.setName(dto.getName());
        u.setActive(dto.isActive());
        // Asignar rol y flags de cuenta
        u.setRole(Role.USER);
        u.setExpired(false);
        u.setLocked(false);
        u.setCredentialsExpired(false);
        // Crear en servicio
        User saved = service.create(u, companyId);
        return ResponseEntity.ok(mapToDto(saved));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> list(
            @PathVariable Long companyId
    ) {
        List<User> users = service.findAllByCompany(companyId);
        List<UserResponseDto> result = users.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> get(
            @PathVariable Long companyId,
            @PathVariable Long id
    ) {
        User u = service.findById(id);
        return ResponseEntity.ok(mapToDto(u));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(
            @PathVariable Long companyId,
            @PathVariable Long id,
            @RequestBody UserRequestDto dto
    ) {
        // Solo actualiza campos permitidos
        User u = new User();
        u.setName(dto.getName());
        u.setActive(dto.isActive());
        User updated = service.update(id, u);
        return ResponseEntity.ok(mapToDto(updated));
    }

    private UserResponseDto mapToDto(User u) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(u.getId());
        dto.setEmail(u.getEmail());
        dto.setName(u.getName());
        dto.setActive(u.isActive());
        dto.setCompanyId(u.getCompany().getId());
        dto.setRequestIds(
                u.getRequests().stream().map(r -> r.getId()).collect(Collectors.toList())
        );
        dto.setLimitIds(
                u.getLimits().stream().map(l -> l.getId()).collect(Collectors.toList())
        );
        return dto;
    }
}