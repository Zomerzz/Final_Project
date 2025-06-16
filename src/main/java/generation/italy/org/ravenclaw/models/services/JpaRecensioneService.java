package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.entities.FilmVisto;
import generation.italy.org.ravenclaw.models.entities.LibroLetto;
import generation.italy.org.ravenclaw.models.entities.Recensione;
import generation.italy.org.ravenclaw.models.entities.VideogiocoGiocato;
import generation.italy.org.ravenclaw.models.repositories.FilmVistoRepository;
import generation.italy.org.ravenclaw.models.repositories.LibroLettoRepository;
import generation.italy.org.ravenclaw.models.repositories.RecensioneRepository;
import generation.italy.org.ravenclaw.models.repositories.VideogiocoGiocatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JpaRecensioneService implements RecensioneService{
    private RecensioneRepository recensioneRepo;
    private FilmVistoRepository filmVistoRepo;
    private LibroLettoRepository libroLettoRepo;
    private VideogiocoGiocatoRepository videogiocoGiocatoRepo;

    @Autowired
    public JpaRecensioneService(RecensioneRepository recensioneRepo,
                                FilmVistoRepository filmVistoRepo,
                                LibroLettoRepository libroLettoRepo,
                                VideogiocoGiocatoRepository videogiocoGiocatoRepo){
        this.recensioneRepo = recensioneRepo;
        this.filmVistoRepo = filmVistoRepo;
        this.libroLettoRepo = libroLettoRepo;
        this.videogiocoGiocatoRepo = videogiocoGiocatoRepo;
    }

    @Override
    public Optional<Recensione> findRecensioneById(int id){
        return recensioneRepo.findById(id);
    }

    @Override
    public List<Recensione> findRecensioni(String mediaType, Integer mediaId) {
        List<Recensione> recensioni = switch (mediaType) {
            case "films" -> findRecensioneByFilmId(mediaId);
            case "libro" -> findRecensioneByLibroId(mediaId);
            case "videogiochi" -> findRecensioneByVideogiocoId(mediaId);
            default -> new ArrayList<>();
        };
        System.out.println(recensioni);
        return recensioni;
    }

    @Override
    public List<Recensione> findAllRecensioni(){
        return recensioneRepo.findAll();
    }

    @Override
    public Recensione saveRecensione(Recensione recensione){
        return recensioneRepo.save(recensione);
    }

    @Override
    public Recensione updateRecensione(Recensione recensione){
        return recensioneRepo.save(recensione);
    }

    @Override
    public boolean deleteRecensione(int id){
        recensioneRepo.deleteById(id);
        return true;
    }

    @Override
    public List<Recensione> findRecensioneByFilmId(int filmId) {
        return recensioneRepo.findByFilmVistoFilmFilmId(filmId);
    }

    @Override
    public List<Recensione> findRecensioneByLibroId(int libroId) {
        return recensioneRepo.findByLibroLettoLibroLibroId(libroId);
    }

    @Override
    public List<Recensione> findRecensioneByVideogiocoId(int videogiocoId) {
        return recensioneRepo.findByVideogiocoGiocatoVideogiocoVideogiocoId(videogiocoId);
    }

    @Override
    public void addRecensioneToFilmVisto(int utenteId, int filmId, Recensione recensione) throws generation.italy.org.ravenclaw.exceptions.EntityNotFoundException {
        Optional<FilmVisto> opt = filmVistoRepo.findByUtenteUtenteIdAndFilmFilmId(utenteId, filmId);
        FilmVisto filmVisto = opt.orElseThrow(()-> new EntityNotFoundException(FilmVisto.class));
        filmVisto.setRecensione(recensione);
        filmVistoRepo.save(filmVisto);
    }

    @Override
    public void addRecensioneToLibroLetto(int utenteId, int libroId, Recensione recensione) throws EntityNotFoundException {
        Optional<LibroLetto> opt = libroLettoRepo.findByUtenteUtenteIdAndLibroLibroId(utenteId, libroId);
        System.out.println(libroId + "           " + utenteId);
        LibroLetto libroLetto = opt.orElseThrow(()-> new EntityNotFoundException(LibroLetto.class));
        libroLetto.setRecensione(recensione);
        libroLettoRepo.save(libroLetto);
    }

    @Override
    public void addRecensioneToVideogiocoGiocato(int utenteId, int videogiocoId, Recensione recensione) throws EntityNotFoundException {
        Optional<VideogiocoGiocato> opt = videogiocoGiocatoRepo.findByUtenteUtenteIdAndVideogiocoVideogiocoId(utenteId, videogiocoId);
        VideogiocoGiocato videogiocoGiocato = opt.orElseThrow(()-> new EntityNotFoundException(VideogiocoGiocato.class));
        videogiocoGiocato.setRecensione(recensione);
        videogiocoGiocatoRepo.save(videogiocoGiocato);
    }

    @Override
    public void addRecensioneToRegistrazione(String type, int utenteId, int mediaId, Recensione recensione) throws EntityNotFoundException {
        switch(type) {
            case "films":
                addRecensioneToFilmVisto(utenteId, mediaId, recensione);
                break;
            case "libro":
                addRecensioneToLibroLetto(utenteId, mediaId, recensione);
                break;
            case "videogiochi":
                addRecensioneToVideogiocoGiocato(utenteId, mediaId, recensione);
                break;
        }
    }
}
