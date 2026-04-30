package application.demo.service;

import application.demo.dto.UserResponseDTO;
import application.demo.entity.Role;
import application.demo.entity.User;
import application.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import application.demo.dto.RegisterRequestDTO;
import application.demo.dto.LoginRequestDTO;
import application.demo.dto.UserResponseDTO;

import java.util.List;
@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO register(RegisterRequestDTO dto) {

        // DTO → Entity
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(Role.USER);

        User saved = userRepository.save(user);

        // Entity → DTO
        return mapToDTO(saved);
    }

    public UserResponseDTO login(LoginRequestDTO dto) {

        User existing = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!existing.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return mapToDTO(existing);
    }

    public void deleteUser(Long id, String role) {

        if (!role.equals("ADMIN")) {
            throw new RuntimeException("Only admin allowed");
        }

        userRepository.deleteById(id);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private UserResponseDTO mapToDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }
}