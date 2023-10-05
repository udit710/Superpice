package au.edu.rmit.sept.superprice.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.User;

@SpringBootTest
public class UserTest {
    User user;

    @BeforeEach
    void createUser() {
        this.user = new User(1l, "test", "test", "test", "test", "test", "test", 1l);
    }

    @Test
    void should_return_id() {
        assertEquals(1l, this.user.getUserId());
    }

    @Test
    void should_return_addressId() {
        this.user.setAddressId(2l);
        assertEquals(2l, this.user.getAddressId());
    }

    @Test
    void should_return_username() {
        this.user.setUsername("testing");
        assertEquals("testing", this.user.getUsername());
    }

    @Test
    void should_return_Password() {
        this.user.setPassword("testing");
        assertEquals("testing", this.user.getPassword());
    }

    @Test
    void should_return_Email() {
        this.user.setEmail("testing");
        assertEquals("testing", this.user.getEmail());
    }

    @Test
    void should_return_FirstName() {
        this.user.setFirstName("testing");
        assertEquals("testing", this.user.getFirstName());
    }

    @Test
    void should_return_LastName() {
        this.user.setLastName("testing");
        assertEquals("testing", this.user.getLastName());
    }

    @Test
    void should_return_Phone() {
        this.user.setPhone("testing");
        assertEquals("testing", this.user.getPhone());
    }

    @Test
    void user_email_constructor() {
        User user = new User("email");
        assertEquals("email", user.getEmail());
    }
}
