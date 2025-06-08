package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.entities.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AutoreService {
    Optional<Autore> findAutoreById(int id);

    List<Autore> findAllAutori() ; //mancava una d

    Autore saveAutore(Autore autore);

    Autore updateAutore(Autore autore) ;

    boolean deleteAutore(int id);
}
