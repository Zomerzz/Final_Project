package generation.italy.org.ravenclaw.models.repositories;

import generation.italy.org.ravenclaw.models.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository  extends JpaRepository<Film, Integer> {

    @Query("SELECT f FROM FilmVisto fv JOIN fv.film f WHERE fv.utente.id = :utenteId")
    List<Film> findByUser(@Param("utenteId") int utenteId);
}

