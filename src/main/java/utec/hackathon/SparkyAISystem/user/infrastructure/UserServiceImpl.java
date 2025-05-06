package utec.hackathon.SparkyAISystem.user.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import utec.hackathon.SparkyAISystem.user.domain.UserService;
import utec.hackathon.SparkyAISystem.user.domain.User;
import utec.hackathon.SparkyAISystem.company.infrastructure.CompanyRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final CompanyRepository companyRepo;

    @Override
    public User create(User user, Long companyId) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setCompany(
                companyRepo.findById(companyId)
                        .orElseThrow(() -> new RuntimeException("Company not found"))
        );
        return repo.save(user);
    }

    @Override
    public List<User> findAllByCompany(Long companyId) {
        return repo.findAll().stream()
                .filter(u -> u.getCompany().getId().equals(companyId))
                .toList();
    }

    @Override
    public User findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User update(Long id, User user) {
        User existing = findById(id);
        existing.setName(user.getName());
        existing.setActive(user.isActive());
        return repo.save(existing);
    }
}