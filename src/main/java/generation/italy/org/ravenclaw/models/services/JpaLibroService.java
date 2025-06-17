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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JpaLibroService implements LibroService{
    private LibroRepository libroRepo;
    private CasaRepository casaRepo;
    private CriteriaLibroRepository criteriaLibroRepo;
    private LibroLettoRepository libroLettoRepo;
    private UtenteRepository utenteRepo;
    private RecensioneRepository recensioneRepo;
    private TagRepository tagRepo;

    @Autowired
    public JpaLibroService(LibroRepository libroRepo,
                           CasaRepository casaRepo,
                           CriteriaLibroRepository criteriaLibroRepo,
                           LibroLettoRepository libroLettoRepo,
                           UtenteRepository utenteRepo,
                           RecensioneRepository recensioneRepo,
                           TagRepository tagRepo){
        this.libroRepo = libroRepo;
        this.casaRepo = casaRepo;
        this.criteriaLibroRepo = criteriaLibroRepo;
        this.libroLettoRepo = libroLettoRepo;
        this.utenteRepo = utenteRepo;
        this.recensioneRepo = recensioneRepo;
        this.tagRepo = tagRepo;
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
        Optional<Casa> optionalCasa = casaRepo.findById(casaEditriceId);
        Casa casaEditrice = optionalCasa.orElseThrow(()-> new EntityNotFoundException(Casa.class));
        libro.setCasaEditrice(casaEditrice);
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
    public Page<Libro> searchLibro(LibroFilterCriteria filters) {
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
    public LibroLetto updateLibroLetto(LibroLetto libroLetto, int libroId, int utenteId, int recensioneId) throws EntityNotFoundException {
        libroLetto.setRecensione(recensioneRepo.findById(recensioneId).orElseThrow(()-> new EntityNotFoundException(Recensione.class)));
        return saveLibroLetto(libroLetto, libroId, utenteId);
    }

    @Override
    public LibroLetto saveLibroLetto(LibroLetto libroLetto, int libroId, int utenteId) throws EntityNotFoundException {
        Optional<Utente> optionalUtente = utenteRepo.findById(utenteId);
        Optional<Libro> optionalLibro = libroRepo.findById(libroId);

        Utente u = optionalUtente.orElseThrow(() -> new EntityNotFoundException(Utente.class));
        Libro l = optionalLibro.orElseThrow(() -> new EntityNotFoundException(Libro.class));

        libroLetto.setLibro(l);
        libroLetto.setUtente(u);

        return libroLettoRepo.save(libroLetto);
    }

    @Override
    public boolean deleteLibroLetto(int id) {
        libroLettoRepo.deleteById(id);
        return true;
    }

    @Override
    public Optional<LibroLetto> findLibroLettoByLibroIdAndUtenteId(int libroId, int utenteId) {
        return libroLettoRepo.findByUtenteUtenteIdAndLibroLibroId(utenteId,libroId);
    }

    @Override
    public List<Libro> findLibriConsigliatiByUserId(int utenteId) {
        List<Integer> favouriteTagsIds = tagRepo.findFavouriteTagsByUserId(utenteId);
        var bestFiveTags = favouriteTagsIds.stream().limit(5).toList();
        List<Integer> favouriteGenresIds = tagRepo.findFavouriteGenresByUserId(utenteId);
        var bestFiveGenres = favouriteGenresIds.stream().limit(5).toList();

        var letti = libroRepo.findByUser(utenteId);
        List<Libro> all = libroRepo.findAll();
        var consigliati = all.stream().filter(libro -> {
            int counter = 0;
            int limit = 1;
            for(Integer t : bestFiveGenres){
                Set<Integer> libroTagsIds = libro.getTagSet().stream().map(Tag::getTagId).collect(Collectors.toSet());
                if(libroTagsIds.contains(t)){
                    counter ++; //al momento lasciamo il limit a uno ma potremmo aumentare in futuro
                }
                if(counter >= limit) {
                    return true;
                }
            }
            return false;
        })
                .filter(libro -> {
            Set<Integer> libroTagsIds = libro.getTagSet().stream().map(Tag::getTagId).collect(Collectors.toSet());
            int counter = 0;
            int limit = 1;
            for(Integer t: bestFiveTags){
                if(libroTagsIds.contains(t)){
                    counter ++; //al momento lasciamo il limit a uno ma potremmo aumentare in futuro
                }
                if(counter >= limit) {
                    return true;
                }
            }
            return false;
        })
                .filter(libro -> !letti.contains(libro)).toList();

        return consigliati;
    }
}
