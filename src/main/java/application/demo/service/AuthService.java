package application.demo.service;

import application.demo.entity.Role;
import application.demo.entity.User;
import application.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User register(User user) {
        user.setRole(Role.USER); // business logic
        return userRepository.save(user); // DB operation
    }
    public User login(String email, String password) {

        User existing = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!existing.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return existing;
    }
    public void deleteUser(Long id, String role) {

        if (!role.equals("ADMIN")) {
            throw new RuntimeException("Only admin allowed");
        }

        userRepository.deleteById(id);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
