package utec.hackathon.SparkyAISystem.user.domain;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean active = true;

    // Relación con Company
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private utec.hackathon.SparkyAISystem.company.domain.Company company;

    // Historial de solicitudes
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<utec.hackathon.SparkyAISystem.request.domain.RequestLog> requests;

    // Límites específicos del usuario
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<utec.hackathon.SparkyAISystem.restriction.domain.UserLimit> limits;
}