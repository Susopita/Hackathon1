package utec.hackathon.SparkyAISystem.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import utec.hackathon.SparkyAISystem.user.domain.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}