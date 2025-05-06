package utec.hackathon.SparkyAISystem.user.domain;

import utec.hackathon.SparkyAISystem.Company.infrastructure.CompanyRepository;
import utec.hackathon.SparkyAISystem.user.infrastructure.UserRepository;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final CompanyRepository companyRepo;

    public User create(User user, Long companyId) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setCompany(
                companyRepo.findById(companyId)
                        .orElseThrow(() -> new RuntimeException("Company not found")));
        return repo.save(user);
    }

    public List<User> findAllByCompany(Long companyId) {
        return repo.findAll().stream()
                .filter(u -> u.getCompany().getId().equals(companyId))
                .toList();
    }

    public User findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User update(Long id, User user) {
        User existing = findById(id);
        existing.setName(user.getName());
        existing.setActive(user.isActive());
        return repo.save(existing);
    }
}