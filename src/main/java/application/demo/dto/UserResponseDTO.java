package application.demo.dto;

import application.demo.entity.Role;
import lombok.Data;
@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Role role;

    // getters & setters
}