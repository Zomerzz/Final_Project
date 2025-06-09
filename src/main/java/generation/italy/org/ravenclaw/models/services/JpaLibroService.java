package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.exceptions.DataException;
import generation.italy.org.ravenclaw.models.entities.Casa;
import generation.italy.org.ravenclaw.models.entities.Libro;
import generation.italy.org.ravenclaw.models.repositories.CasaRepository;
import generation.italy.org.ravenclaw.models.repositories.LibroRepository;
import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.repositories.criteriaRepositories.CriteriaLibroRepositoryImpl;
import generation.italy.org.ravenclaw.models.searchCriteria.LibroFilterCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaLibroService implements LibroService{
    private LibroRepository libroRepo;
    private CasaRepository casaRepo;
    private CriteriaLibroRepositoryImpl criteriaLibroRepo;

    @Autowired
    public JpaLibroService(LibroRepository libroRepo, CasaRepository casaRepo, CriteriaLibroRepositoryImpl criteriaLibroRepo){
        this.libroRepo = libroRepo;
        this.casaRepo = casaRepo;
        this.criteriaLibroRepo = criteriaLibroRepo;
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
}
