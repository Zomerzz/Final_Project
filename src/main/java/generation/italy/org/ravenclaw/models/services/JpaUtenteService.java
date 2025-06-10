
package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.dtos.request.PasswordUpdateRequest;
import generation.italy.org.ravenclaw.models.dtos.response.UtenteResponse;
import generation.italy.org.ravenclaw.models.entities.Authority;
import generation.italy.org.ravenclaw.models.entities.Utente;
import generation.italy.org.ravenclaw.models.repositories.UtenteRepository;
import generation.italy.org.ravenclaw.models.services.utils.FindAuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class JpaUtenteService implements UtenteService{
    private UtenteRepository utenteRepo;
    private final FindAuthenticatedUser findAuthenticatedUser;
    private final PasswordEncoder passwordEncoder;


    public JpaUtenteService(UtenteRepository utenteRepo, FindAuthenticatedUser findAuthenticatedUser, PasswordEncoder passwordEncoder) {
        this.utenteRepo = utenteRepo;
        this.findAuthenticatedUser = findAuthenticatedUser;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public UtenteResponse getUserInfo() {
        Utente utente = findAuthenticatedUser.getAuthenticatedUser();
        return new UtenteResponse(
                utente.getUtenteId(),
                utente.getNome(),
                utente.getEmail(),
                utente.getAuthorities().stream().map(auth -> (Authority) auth).toList()
        );
    }


    @Override
    public Optional<Utente> findUtenteById(int id)  {
        Optional<Utente> utenteOpt = utenteRepo.findById(id);
        return utenteOpt;
    }

    @Override
    public List<Utente> findAllUtenti() {
        List<Utente> utenti = utenteRepo.findAll();
        return utenti;
    }

    @Override
    public Utente saveUtente(Utente utente) {
        return utenteRepo.save(utente);
    }

    @Override
    public Utente updateUtente(Utente utente) {
        Optional<Utente> optUtente = utenteRepo.findById(utente.getUtenteId());

        return utenteRepo.save(utente);
    }

    @Override
    public boolean deleteUtente(int id) {
        Utente utente = findAuthenticatedUser.getAuthenticatedUser();
        if(isLastAdmin(utente)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Un Admin non può cancellare se stesso");
        }
        utenteRepo.deleteById(id);
        return true;
    }

    @Transactional
    @Override
    public void updatePassword(PasswordUpdateRequest passwordUpdateRequest) {
        Utente user = findAuthenticatedUser.getAuthenticatedUser();

        if (!isOldPasswordCorrect(user.getPassword(), passwordUpdateRequest.getVecchiaPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password attuale errata");
        }

        if (!isNewPasswordConfirmed(passwordUpdateRequest.getNewPassword(),
                passwordUpdateRequest.getNewPasswordRepeat())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le nuove password non coincidono");
        }

        if (!isNewPasswordDifferent(passwordUpdateRequest.getVecchiaPassword(),
                passwordUpdateRequest.getNewPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La vecchia password e la nuova password non possono essere uguali");
        }

        user.setPassword(passwordEncoder.encode(passwordUpdateRequest.getNewPassword()));
        utenteRepo.save(user);
    }

    private boolean isOldPasswordCorrect(String currentPassword, String oldPassword) {
        return passwordEncoder.matches(oldPassword, currentPassword);
    }

    private boolean isNewPasswordConfirmed(String newPassword, String newPasswordConfirmation) {
        return newPassword.equals(newPasswordConfirmation);
    }

    private boolean isNewPasswordDifferent(String oldPassword, String newPassword) {
        return !oldPassword.equals(newPassword);
    }

    private boolean isLastAdmin(Utente user) {
        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(authority -> "ROLE_ADMIN".equals(authority.getAuthority()));

        if (isAdmin) {
            long adminCount = utenteRepo.countAdminUsers();
            return adminCount <= 1;
        }

        return false;
    }



}

