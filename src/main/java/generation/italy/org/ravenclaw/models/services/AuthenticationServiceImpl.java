package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.dtos.request.AuthenticationRequest;
import generation.italy.org.ravenclaw.models.dtos.request.RegisterRequest;
import generation.italy.org.ravenclaw.models.dtos.response.AuthenticationResponse;
import generation.italy.org.ravenclaw.models.entities.Authority;
import generation.italy.org.ravenclaw.models.entities.Utente;
import generation.italy.org.ravenclaw.models.repositories.UtenteRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UtenteRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationServiceImpl(UtenteRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional
    public void register(RegisterRequest input) throws Exception {
        if (isEmailTaken(input.getEmail())) {
            throw new Exception("Email already taken");
        }
        Utente user = buildNewUser(input);
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthenticationResponse login(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Utente user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        String jwtToken = jwtService.generateToken(new HashMap<>(), user);

        return new AuthenticationResponse(jwtToken);
    }

    private boolean isEmailTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private Utente buildNewUser(RegisterRequest input) {
        Utente user = new Utente();
        user.setUtenteId(0);
        user.setNome(input.getName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setAuthorities(initialAuthority());
        return user;
    }

    private List<Authority> initialAuthority() {
        boolean isFirstUser = userRepository.count() == 0;
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority("ROLE_EMPLOYEE"));
        if (isFirstUser) {
            authorities.add(new Authority("ROLE_ADMIN"));
        }
        return authorities;
    }
}
