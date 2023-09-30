package au.edu.rmit.sept.superprice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import au.edu.rmit.sept.superprice.model.User;
import au.edu.rmit.sept.superprice.service.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveOrUpdateUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        // Logic to update the user based on the provided ID
        return userService.saveOrUpdateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/username/{username}")
    public User getUsersByUsername(@PathVariable String username) {
        return userService.getUsersByUsername(username);
    }

    @GetMapping("/email/{email}")
    public User getUsersByEmail(@PathVariable String email) {
        return userService.getUsersByEmail(email);
    }

    @GetMapping("/firstName/{firstName}")
    public List<User> getUsersByFirstName(@PathVariable String firstName) {
        return userService.getUsersByFirstName(firstName);
    }

    @GetMapping("/lastName/{lastName}")
    public List<User> getUsersByLastName(@PathVariable String lastName) {
        return userService.getUsersByLastName(lastName);
    }

    @GetMapping("/phone/{phone}")
    public List<User> getUsersByPhone(@PathVariable String phone) {
        return userService.getUsersByPhone(phone);
    }

    @GetMapping("/addressId/{addressId}")
    public List<User> getUsersByAddressId(@PathVariable Long addressId) {
        return userService.getUsersByAddressId(addressId);
    }

}
