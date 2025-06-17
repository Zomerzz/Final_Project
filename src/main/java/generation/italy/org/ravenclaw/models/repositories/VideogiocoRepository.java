package generation.italy.org.ravenclaw.models.repositories;

import generation.italy.org.ravenclaw.models.entities.Videogioco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideogiocoRepository extends JpaRepository<Videogioco, Integer> {

    @Query("SELECT v FROM VideogiocoGiocato vg JOIN vg.videogioco v WHERE vg.utente.id = :utenteId")
    List<Videogioco> findByUser(@Param("utenteId") int utenteId);
}

