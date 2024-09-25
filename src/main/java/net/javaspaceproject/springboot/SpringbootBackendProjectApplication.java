package net.javaspaceproject.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.javaspaceproject.springboot.model.User;
import net.javaspaceproject.springboot.repository.UserRepository;

@SpringBootApplication
public class SpringbootBackendProjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendProjectApplication.class, args);
	}
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		this.userRepository.save(new User("John", "May","johnmay@gmail.com"));	
		this.userRepository.save(new User("Ben", "Ten","benten@gmail.com"));
		this.userRepository.save(new User("Melissa", "Robinson","melissa@gmail.com"));
	}
	
	

}
