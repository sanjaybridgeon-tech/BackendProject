package application.demo.controller;

import application.demo.dto.LoginRequestDTO;
import application.demo.dto.RegisterRequestDTO;
import application.demo.dto.UserResponseDTO;
import application.demo.entity.Role;
import application.demo.entity.User;
import application.demo.repository.UserRepository;
import application.demo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }



    // ✅ REGISTER
    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody RegisterRequestDTO dto) {
        return authService.register(dto);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto) {

        try {
            UserResponseDTO user = authService.login(dto);

            return ResponseEntity.ok(Map.of(
                    "id", user.getId(),
                    "role", user.getRole()
            ));

        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id,
                                        @RequestParam String role) {
        try {
            authService.deleteUser(id, role);
            return ResponseEntity.ok("User deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }
    @GetMapping("/all")
    public List<UserResponseDTO> getAllUsers() {
        return authService.getAllUsers();
    }





}

