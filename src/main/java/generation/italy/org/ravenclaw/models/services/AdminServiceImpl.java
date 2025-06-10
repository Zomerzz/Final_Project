package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.dtos.response.UtenteResponse;
import generation.italy.org.ravenclaw.models.entities.Authority;
import generation.italy.org.ravenclaw.models.entities.Utente;
import generation.italy.org.ravenclaw.models.repositories.UtenteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class AdminServiceImpl implements AdminService{
    private final UtenteRepository utenteRepo;

    public AdminServiceImpl(UtenteRepository utenteRepo) {
        this.utenteRepo = utenteRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UtenteResponse> getAllUsers() {
        return StreamSupport.stream(utenteRepo.findAll().spliterator(), false)
                .map(this::convertToUserResponse).toList();
    }

    @Transactional
    @Override
    public UtenteResponse promoteToAdmin(int userId) {
        Optional<Utente> user = utenteRepo.findById(userId);

        if (user.isEmpty() || user.get().getAuthorities().stream().anyMatch(authority -> "ROLE_ADMIN"
                .equals(authority.getAuthority()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "L'utente non esiste o è già un Admin");
        }

        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority("ROLE_BASE"));
        authorities.add(new Authority("ROLE_ADMIN"));
        user.get().setAuthorities(authorities);

        Utente savedUser = utenteRepo.save(user.get());

        return convertToUserResponse(savedUser);
    }

    @Transactional
    @Override
    public void deleteNonAdminUser(int userId) {
        Optional<Utente> user = utenteRepo.findById(userId);

        if (user.isEmpty() || user.get().getAuthorities().stream().anyMatch(authority -> "ROLE_ADMIN"
                .equals(authority.getAuthority()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "L'utente non esiste o è un Admin");
        }

        utenteRepo.delete(user.get());
    }

    private UtenteResponse convertToUserResponse(Utente user) {
        return new UtenteResponse(
                user.getUtenteId(),
                user.getNome(),
                user.getEmail(),
                user.getAuthorities().stream().map(auth -> (Authority) auth).toList());
    }
}
