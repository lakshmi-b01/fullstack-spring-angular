package net.javaspaceproject.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaspaceproject.springboot.model.User;
import net.javaspaceproject.springboot.repository.UserRepository;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	@PostMapping
	public User postUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@GetMapping(value = "/{id}")
	public Optional<User> getUsersById(@PathVariable long id){
		return userRepository.findById(id);
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteUser(@PathVariable long id) {
		userRepository.deleteById(id);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {
	    Optional<User> existingUserOptional = userRepository.findById(id);
	    if (existingUserOptional.isPresent()) {
	        User existingUser = existingUserOptional.get();
	        
	        existingUser.setFirstName(user.getFirstName());
	        existingUser.setLastName(user.getLastName());
	        existingUser.setEmailId(user.getEmailId());
	        
	        User updatedUser = userRepository.save(existingUser);
	        return ResponseEntity.ok(updatedUser);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
	}


}
