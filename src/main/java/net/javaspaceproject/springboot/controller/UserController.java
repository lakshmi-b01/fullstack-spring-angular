package net.javaspaceproject.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.javaspaceproject.springboot.service.UserService;
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

	@Autowired
	private UserService userService;
	
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<User>> getUsers(){
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<User> postUser(@RequestBody User user) {
		userService.addUser(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Optional<User>> getUsersById(@PathVariable long id){
		Optional<User> user = userService.getUserById(id);
		return user.map(value -> new ResponseEntity<>(Optional.of(value), HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Void> deleteUser(@PathVariable long id) {
		boolean deleted = userService.deleteUserById(id);
		if(deleted){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {
	    boolean updated = userService.updateUserById(id, user);
		if(updated){
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


}
