package generation.italy.org.ravenclaw.models.services;

import generation.italy.org.ravenclaw.models.dtos.response.UtenteResponse;
import org.springframework.stereotype.Service;
import java.util.List;

public interface AdminService {
    List<UtenteResponse> getAllUsers();
    UtenteResponse promoteToAdmin(int userId);
    void deleteNonAdminUser(int userId);

}
