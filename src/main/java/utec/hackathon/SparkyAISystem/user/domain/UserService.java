package utec.hackathon.SparkyAISystem.user.application;

import utec.hackathon.SparkyAISystem.user.domain.User;
import java.util.List;

public interface UserService {
    User create(User user, Long companyId);
    List<User> findAllByCompany(Long companyId);
    User findById(Long id);
    User update(Long id, User user);
}