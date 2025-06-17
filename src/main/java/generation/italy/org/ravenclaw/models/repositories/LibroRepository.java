package generation.italy.org.ravenclaw.models.repositories;

import generation.italy.org.ravenclaw.models.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Book;
import java.util.List;

public interface LibroRepository  extends JpaRepository<Libro, Integer> {

    @Query("SELECT l FROM LibroLetto ll JOIN ll.libro l WHERE ll.utente.id = :utenteId")
    List<Libro> findByUser(@Param("utenteId") int utenteId);
}
