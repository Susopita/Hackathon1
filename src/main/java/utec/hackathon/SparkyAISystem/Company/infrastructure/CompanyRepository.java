package utec.hackathon.SparkyAISystem.Company.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utec.hackathon.SparkyAISystem.Company.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
