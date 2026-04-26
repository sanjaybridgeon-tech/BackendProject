package application.demo;

import application.demo.entity.Role;
import application.demo.entity.User;
import application.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// 🔥 ADD THIS PART
	@Bean
	CommandLineRunner init(UserRepository repo) {
		return args -> {
			if (repo.findByEmail("admin@gmail.com").isEmpty()) {

				User admin = new User();
				admin.setEmail("admin@gmail.com");
				admin.setPassword("123"); // simple for now
				admin.setRole(Role.ADMIN);

				repo.save(admin);
			}
		};
	}
}