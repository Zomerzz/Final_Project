package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.exceptions.DataException;
import generation.italy.org.ravenclaw.models.dtos.LibroDto;
import generation.italy.org.ravenclaw.models.dtos.RecensioneDto;
import generation.italy.org.ravenclaw.models.entities.*;
import generation.italy.org.ravenclaw.models.repositories.*;
import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.repositories.criteriaRepositories.CriteriaLibroRepository;
import generation.italy.org.ravenclaw.models.searchCriteria.LibroFilterCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaLibroService implements LibroService{
    private LibroRepository libroRepo;
    private CasaRepository casaRepo;
    private CriteriaLibroRepository criteriaLibroRepo;
    private LibroLettoRepository libroLettoRepo;
    private UtenteRepository utenteRepo;

    @Autowired
    public JpaLibroService(LibroRepository libroRepo,
                           CasaRepository casaRepo,
                           CriteriaLibroRepository criteriaLibroRepo,
                           LibroLettoRepository libroLettoRepo,
                           UtenteRepository utenteRepo){
        this.libroRepo = libroRepo;
        this.casaRepo = casaRepo;
        this.criteriaLibroRepo = criteriaLibroRepo;
        this.libroLettoRepo = libroLettoRepo;
        this.utenteRepo = utenteRepo;;
    }

    @Override
    public Optional<Libro> findLibroById(int id) throws DataException {
        return libroRepo.findById(id);
    }

    @Override
    public List<Libro> findAllLibri() throws DataException {
        return libroRepo.findAll();
    }

    @Override
    public Libro saveLibro(Libro libro, int casaEditriceId) throws DataException, EntityNotFoundException {
        Optional<Casa> optCDPub = casaRepo.findById(casaEditriceId);
        Casa casaDiPubblicazione = optCDPub.orElseThrow(() -> new EntityNotFoundException(Casa.class));
        libro.setCasaEditrice(casaDiPubblicazione);
        return libroRepo.save(libro);
    }

    @Override
    public Libro updateLibro(Libro libro, int casaEditriceId) throws DataException, EntityNotFoundException {
        return saveLibro(libro, casaEditriceId);
    }

    @Override
    public boolean deleteLibro(int id) throws DataException, EntityNotFoundException {
        libroRepo.deleteById(id);
        return true;
    }

    @Override
    public List<Libro> searchLibro(LibroFilterCriteria filters) {
        return criteriaLibroRepo.searchLibroByFilters(filters);
    }

    @Override
    public List<LibroLetto> findLibroLettoByUtente(int utenteId) {
        return libroLettoRepo.findByUtenteUtenteId(utenteId);
    }

    @Override
    public Optional<LibroLetto> findLibroLettoById(int id) {
        return libroLettoRepo.findById(id);
    }

    @Override
    public LibroLetto updateLibroLetto(LibroLetto libroLetto, Libro libro, int utenteId, Recensione recensione) throws EntityNotFoundException {
        return saveLibroLetto(libroLetto, libro, utenteId, recensione);
    }

    @Override
    public LibroLetto saveLibroLetto(LibroLetto libroLetto, Libro libro, int utenteId, Recensione recensione) throws EntityNotFoundException {
        Optional<Utente> optionalUtente = utenteRepo.findById(utenteId);

        Utente u = optionalUtente.orElseThrow(() -> new EntityNotFoundException(Utente.class));

        if(recensione != null){
            libroLetto.setRecensione(recensione);
        }

        libroLetto.setLibro(libro);
        libroLetto.setUtente(u);

        return libroLettoRepo.save(libroLetto);
    }

    @Override
    public boolean deleteLibroLetto(int id) {
        libroLettoRepo.deleteById(id);
        return true;
    }
}
