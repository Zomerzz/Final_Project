package generation.italy.org.ravenclaw.controllers;

import generation.italy.org.ravenclaw.models.dtos.response.UtenteResponse;
import generation.italy.org.ravenclaw.models.services.AdminService;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UtenteResponse> getAllUsers() {
        return adminService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{userId}/role")
    public UtenteResponse promoteToAdmin(@PathVariable @Min(1) int userId) {
        return adminService.promoteToAdmin(userId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable @Min(1) int userId) {
        adminService.deleteNonAdminUser(userId);
    }

}
