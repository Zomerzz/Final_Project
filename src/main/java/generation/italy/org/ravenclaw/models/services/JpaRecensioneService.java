package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Recensione;
import generation.italy.org.ravenclaw.models.repositories.RecensioneRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaRecensioneService implements RecensioneService{
    private RecensioneRepository recensioneRepo;

    @Autowired
    public JpaRecensioneService(RecensioneRepository recensioneRepo){
        this.recensioneRepo = recensioneRepo;
    }

    @Override
    public Optional<Recensione> findRecensioneById(int id){
        return recensioneRepo.findById(id);
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
//
     //DA GESTIRE IL FATTO CHE NON COMPARE NEL DTO IL NOME DELL'OPERA, COSì NELLA PAG UTENTE SAREBBE UNA LISTA DI RECENSIONI A CASO
//    @Override
//    public List<Recensione> findRecensioneByUtenteId(int utenteId) {
//        List<Recensione> recFilm = recensioneRepo.findByFilmVistoFilmUtenteId(utenteId);
//        List<Recensione> recLibri = recensioneRepo.findByLibroLettoLibroUtenteId(utenteId);
//        List<Recensione> recVideogiochi = recensioneRepo.findByVideogiocoGiocatoVideogiocoUtenteId(utenteId);
//    }
}
