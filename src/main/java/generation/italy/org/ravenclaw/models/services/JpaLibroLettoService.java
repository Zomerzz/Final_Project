package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.repositories.LibroLettoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaLibroLettoService implements LibroLettoService {
    private LibroLettoRepository libroLettoRepo;

    @Autowired
    public JpaLibroLettoService(LibroLettoRepository libroLettoRepo) {
        this.libroLettoRepo = libroLettoRepo;
    }
}
