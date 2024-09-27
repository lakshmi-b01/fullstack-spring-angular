package net.javaspaceproject.springboot.controller;

import net.javaspaceproject.springboot.model.User;
import net.javaspaceproject.springboot.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("John", "May", "johnmay@gmail.com"));

        when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.getUsers();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().size(), 1);
    }

    @Test
    public void testPostUser() {
        User user = new User("John", "May", "johnmay@gmail.com");

        ResponseEntity<User> response = userController.postUser(user);

        verify(userService, times(1)).addUser(user);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetUsersById() {
        User user = new User("John", "May", "johnmay@gmail.com");
        user.setId(1);

        when(userService.getUserById(1)).thenReturn(Optional.of(user));

        ResponseEntity<Optional<User>> response = userController.getUsersById(1);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertTrue(response.getBody().isPresent());
        assertEquals(response.getBody().get().getFirstName(), "John");
    }

    @Test
    public void testDeleteUser() {
        when(userService.deleteUserById(1)).thenReturn(true);

        ResponseEntity<Void> response = userController.deleteUser(1);

        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testUpdateUser() {
        User user = new User("John", "May", "johnmay@gmail.com");
        user.setId(1);

        when(userService.updateUserById(1, user)).thenReturn(true);

        ResponseEntity<User> response = userController.updateUser(1, user);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
