package net.javaspaceproject.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.javaspaceproject.springboot.model.User;
import net.javaspaceproject.springboot.repository.UserRepository;

@SpringBootApplication
//public class SpringbootBackendProjectApplication implements CommandLineRunner {
public class SpringbootBackendProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendProjectApplication.class, args);
	}
	
	@Autowired
	private UserRepository userRepository;

}
