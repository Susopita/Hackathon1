package utec.hackathon.SparkyAISystem.request.domain;

import java.util.List;

public interface RequestLogService {
    RequestLog log(RequestLog log);
    List<RequestLog> findByUser(Long userId);
    List<RequestLog> findByCompany(Long companyId);
}