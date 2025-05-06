package utec.hackathon.SparkyAISystem.request.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utec.hackathon.SparkyAISystem.request.domain.RequestLogService;
import utec.hackathon.SparkyAISystem.request.domain.RequestLog;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestLogServiceImpl implements RequestLogService {
    private final RequestLogRepository repo;

    @Override
    public RequestLog log(RequestLog log) {
        return repo.save(log);
    }

    @Override
    public List<RequestLog> findByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    @Override
    public List<RequestLog> findByCompany(Long companyId) {
        return repo.findByUserCompanyId(companyId);
    }
}