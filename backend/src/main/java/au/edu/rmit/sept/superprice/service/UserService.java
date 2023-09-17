package au.edu.rmit.sept.superprice.service;

import au.edu.rmit.sept.superprice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import au.edu.rmit.sept.superprice.model.User;
import au.edu.rmit.sept.superprice.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findByUserId(userId);
    }


    public User saveOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getUsersByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getUsersByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUsersByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    public List<User> getUsersByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    public List<User> getUsersByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    public List<User> getUsersByAddressId(Long addressId) {
        return userRepository.findByAddressId(addressId);
    }
    
}
