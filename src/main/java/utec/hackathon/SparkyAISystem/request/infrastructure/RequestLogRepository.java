package utec.hackathon.SparkyAISystem.request.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import utec.hackathon.SparkyAISystem.request.domain.RequestLog;
import java.util.List;

public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {
    List<RequestLog> findByUserId(Long userId);
    List<RequestLog> findByUserCompanyId(Long companyId);
}