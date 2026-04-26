package application.demo.controller;

import application.demo.entity.Role;
import application.demo.entity.User;
import application.demo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setRole(Role.USER); // 🔥 always normal user
        return userRepository.save(user);
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }

        User existing = existingUser.get(); // ✅ safe now

        if (!existing.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid password");
        }

        // 🔥 TEMP TOKEN (later JWT)
        return ResponseEntity.ok(Map.of(
                "id", existing.getId(),
                "role", existing.getRole()
        ));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id,
                                        @RequestParam String role) {

        if (!role.equals("ADMIN")) {
            return ResponseEntity.status(403).body("Only admin allowed");
        }

        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }
    // ✅ Get all users
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}