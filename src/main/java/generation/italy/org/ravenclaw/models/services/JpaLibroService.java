package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.entities.Casa;
import generation.italy.org.ravenclaw.models.entities.Libro;
import generation.italy.org.ravenclaw.models.repositories.CasaRepository;
import generation.italy.org.ravenclaw.models.repositories.LibroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaLibroService implements LibroService{
    private LibroRepository libroRepo;
    private CasaRepository casaRepo;

    @Autowired
    public JpaLibroService(LibroRepository libroRepo, CasaRepository casaRepo){
        this.libroRepo = libroRepo;
        this.casaRepo = casaRepo;
    }

    @Override
    public Optional<Libro> findLibroById(int id) {
        return libroRepo.findById(id);
    }

    @Override
    public List<Libro> findAllLibri() {
        return libroRepo.findAll();
    }

    @Override
    public Libro saveLibro(Libro libro, int casaDiProduzioneId) {
        //TODO CAMBIARE LE ECCEZIONI, AL MOMENTO SONO QUELLE DI JAKARTA
        Optional<Casa> optCDPub = casaRepo.findById(casaDiProduzioneId);
        Casa casaDiPubblicazione = optCDPub.orElseThrow(EntityNotFoundException::new);
        libro.setCasaDiProduzione(casaDiPubblicazione);
        return libroRepo.save(libro);
    }

    @Override
    public Libro updateLibro(Libro libro, int casaDiProduzioneId, int casaDiPubblicazioneId) {
        Optional<Libro> optLibro = libroRepo.findById(libro.getLibroId());
        if(optLibro.isEmpty()){
            throw new EntityNotFoundException(); //AL MOMENTO L'EXCEPTION è QUELLA DI JAKARTA
        }
        return saveLibro(libro, casaDiProduzioneId);
    }

    @Override
    public boolean deleteLibro(int id) {
        Optional<Libro> optLibro = libroRepo.findById(id);
        if(optLibro.isEmpty()){
            throw new EntityNotFoundException(); //AL MOMENTO L'EXCEPTION è QUELLA DI JAKARTA
        }
        libroRepo.deleteById(id);
        return true;
    }
}
