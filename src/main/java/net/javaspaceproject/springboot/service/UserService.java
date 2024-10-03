package net.javaspaceproject.springboot.service;

import net.javaspaceproject.springboot.exception.UserNotFoundException;
import net.javaspaceproject.springboot.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();

    public void addUser(User user){
        users.add(user);
    }

    public List<User> getAllUsers(){
        System.out.println("Get all Users");
        return users;
    }

    public Optional<User> getUserById(long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .or(() -> {
                    throw new UserNotFoundException("User not found with id: " + id);
                });
    }



    public boolean updateUserById(long id, User newUser){

        return getUserById(id).map(currUser -> {
            if (users.contains(currUser)) {
                users.remove(currUser);
                users.add(newUser);
                return true;
            }
            return false;
        }).orElse(false);

    }

    public boolean deleteUserById(long id){
        return users.removeIf(user -> user.getId() == id);
    }
}
