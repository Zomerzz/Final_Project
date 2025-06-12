package generation.italy.org.ravenclaw.models.services.utils;

import generation.italy.org.ravenclaw.models.entities.Utente;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class FindAuthenticatedUserImpl implements FindAuthenticatedUser{
    @Override
    public Utente getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() ||
                authentication.getPrincipal().equals("anonymousUser")) {
            throw new AccessDeniedException("Authentication required");
        }

        return (Utente) authentication.getPrincipal();
    }
}
