package generation.italy.org.ravenclaw.models.repositories;

import generation.italy.org.ravenclaw.models.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UtenteRepository  extends JpaRepository<Utente, Integer> {

    Optional<Utente> findByEmail(String email);

    @Query("SELECT COUNT(u) FROM Utente u JOIN u.authorities a WHERE a.authority = 'ROLE_ADMIN'")
    long countAdminUsers();

    List<Utente> findByNomeIgnoreCaseContains(String lowerCase);
}
