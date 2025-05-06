package utec.hackathon.SparkyAISystem.user.domain;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utec.hackathon.SparkyAISystem.Company.domain.Company;
import utec.hackathon.SparkyAISystem.request.domain.RequestLog;
import utec.hackathon.SparkyAISystem.userLimit.domain.UserLimit;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
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
    private Company company;

    // Historial de solicitudes
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<RequestLog> requests;

    // Límites específicos del usuario
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserLimit> limits;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean expired;

    private Boolean locked;

    private Boolean credentialsExpired;

    private Boolean enable;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}