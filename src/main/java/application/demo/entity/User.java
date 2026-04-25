package application.demo.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role; // "USER" or "ADMIN"
    @Column(unique = true)
    private String email;
    private String password;
}