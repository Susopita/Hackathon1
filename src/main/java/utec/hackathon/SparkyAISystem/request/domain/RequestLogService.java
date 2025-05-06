package utec.hackathon.SparkyAISystem.request.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utec.hackathon.SparkyAISystem.request.domain.RequestLog;
import utec.hackathon.SparkyAISystem.request.dto.RequestLogRequestDto;
import utec.hackathon.SparkyAISystem.request.dto.RequestLogResponseDto;
import utec.hackathon.SparkyAISystem.request.infrastructure.RequestLogRepository;
import utec.hackathon.SparkyAISystem.user.infrastructure.UserRepository;
import utec.hackathon.SparkyAISystem.user.domain.User;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestLogService {
    private final RequestLogRepository repo;
    private final UserRepository userRepo;

    public RequestLogResponseDto log(Long userId, RequestLogRequestDto dto) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        RequestLog log = new RequestLog();
        log.setModel(dto.getModel());
        log.setPrompt(dto.getPrompt());
        log.setUser(user);
        RequestLog saved = repo.save(log);
        return mapToDto(saved);
    }

    public List<RequestLogResponseDto> findByUser(Long userId) {
        return repo.findByUserId(userId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<RequestLogResponseDto> findByCompany(Long companyId) {
        return repo.findByUserCompanyId(companyId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private RequestLogResponseDto mapToDto(RequestLog log) {
        RequestLogResponseDto dto = new RequestLogResponseDto();
        dto.setId(log.getId());
        dto.setModel(log.getModel());
        dto.setPrompt(log.getPrompt());
        dto.setResponse(log.getResponse());
        dto.setTokensConsumed(log.getTokensConsumed());
        dto.setEstimatedCost(log.getEstimatedCost());
        dto.setTimestamp(log.getTimestamp());
        dto.setUserId(log.getUser().getId());
        return dto;
    }
}