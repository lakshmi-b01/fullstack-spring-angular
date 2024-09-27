package net.javaspaceproject.springboot.service;


import net.javaspaceproject.springboot.model.User;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    //For setting up/ Initializing methods
    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUser() {
        User user = new User("John", "May", "johnmay@gmail.com");
        userService.addUser(user);
        assertEquals(userService.getAllUsers().size(), 1);
        assertEquals(userService.getAllUsers().getFirst().getFirstName(), "John");
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User("John", "May", "johnmay@gmail.com");
        User user2 = new User("Noddy", "Doe", "noddydoe@yahoo.com");
        userService.addUser(user1);
        userService.addUser(user2);
        assertEquals(userService.getAllUsers().size(), 2);
    }

    @Test
    public void testGetUserById() {
        User user = new User("John", "May", "johnmay@gmail.com");
        user.setId(1);
        userService.addUser(user);
        Optional<User> foundUser = userService.getUserById(1);
        assertTrue(foundUser.isPresent());
        assertEquals(foundUser.get().getFirstName(), "John");
    }

    @Test
    public void testUpdateUserById() {
        User user = new User("John", "May", "johnmay@gmail.com");
        user.setId(1);
        userService.addUser(user);

        User updatedUser = new User("Noddy", "Doe", "noddydoe@yahoo.com");
        updatedUser.setId(1);
        boolean isUpdated = userService.updateUserById(1, updatedUser);

        assertTrue(isUpdated);
        assertEquals(userService.getUserById(1).get().getLastName(), "Doe");
    }

    @Test
    public void testDeleteUserById() {
        User user = new User("John", "May", "johnmay@gmail.com");
        user.setId(1);
        userService.addUser(user);

        boolean isDeleted = userService.deleteUserById(1);
        assertTrue(isDeleted);
        assertEquals(userService.getAllUsers().size(), 0);
    }


}
