package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.dtos.request.AuthenticationRequest;
import generation.italy.org.ravenclaw.models.dtos.request.RegisterRequest;
import generation.italy.org.ravenclaw.models.dtos.response.AuthenticationResponse;

public interface AuthenticationService {
    void register(RegisterRequest input) throws Exception;
    AuthenticationResponse login(AuthenticationRequest request);
}
