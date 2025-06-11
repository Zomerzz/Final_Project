package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.exceptions.EntityNotFoundException;
import generation.italy.org.ravenclaw.models.entities.*;
import generation.italy.org.ravenclaw.models.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaLibroLettoService implements LibroLettoService {
    private LibroLettoRepository libroLettoRepo;
    private LibroRepository libroRepo;
    private UtenteRepository utenteRepo;
    private RecensioneRepository recensioneRepo;

    @Autowired
    public JpaLibroLettoService(LibroLettoRepository libroLettoRepo,
                                       LibroRepository libroRepo,
                                       UtenteRepository utenteRepo,
                                       RecensioneRepository recensioneRepo) {
        this.libroLettoRepo = libroLettoRepo;
        this.libroRepo = libroRepo;
        this.utenteRepo = utenteRepo;
        this.recensioneRepo = recensioneRepo;
    }

    @Override
    public List<LibroLetto> findAll() {
        return libroLettoRepo.findAll();
    }

    @Override
    public List<LibroLetto> findByUtente(int utenteId) {
        return libroLettoRepo.findByUtenteUtenteId(utenteId);
    }

    @Override
    public Optional<LibroLetto> findById(int id) {
        return libroLettoRepo.findById(id);
    }

    @Override
    public LibroLetto update(LibroLetto libroLetto, int libroId, int utenteId, int recensioneId) throws EntityNotFoundException {
        return save(libroLetto, libroId, utenteId, recensioneId);
    }

    @Override
    public LibroLetto save(LibroLetto libroLetto, int libroId, int utenteId, int recensioneId) throws EntityNotFoundException {
        Optional<Libro> optionalLibro = libroRepo.findById(libroId);
        Optional<Utente> optionalUtente = utenteRepo.findById(utenteId);
        Optional<Recensione> optionalRecensione = recensioneRepo.findById(recensioneId);

        Libro l = optionalLibro.orElseThrow(() -> new EntityNotFoundException(Libro.class));
        Utente u = optionalUtente.orElseThrow(() -> new EntityNotFoundException(Utente.class));

        if(optionalRecensione.isPresent()){
            Recensione r = optionalRecensione.get();
            libroLetto.setRecensione(r);
        }

        libroLetto.setLibro(l);
        libroLetto.setUtente(u);

        return libroLettoRepo.save(libroLetto);
    }

    @Override
    public boolean delete(int id) {
        libroLettoRepo.deleteById(id);
        return true;
    }
}
