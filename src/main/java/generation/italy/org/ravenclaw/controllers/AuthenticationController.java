package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.models.dtos.request.AuthenticationRequest;
import generation.italy.org.ravenclaw.models.dtos.request.RegisterRequest;
import generation.italy.org.ravenclaw.models.dtos.response.AuthenticationResponse;
import generation.italy.org.ravenclaw.models.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @ResponseStatus(HttpStatus.CREATED) // crea un nuovo utente
    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterRequest registerRequest) throws Exception {
        authenticationService.register(registerRequest);
    }

    @ResponseStatus(HttpStatus.OK) // fa il login del utente
    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody AuthenticationRequest authRequest) {
        return authenticationService.login(authRequest);
    }
}
