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
    void createReview() {
        this.user = new User(1l, "test", "test", "test", "test", "test", "test", 1l);
    }

    @Test
    void should_return_id() {
        assertEquals(1l, this.user.getUserId());
    }

    @Test
    void should_return_addressId() {
        assertEquals(1l, this.user.getAddressId());
    }

    @Test
    void should_return_username() {
        assertEquals("test", this.user.getUsername());
    }

    @Test
    void should_return_Password() {
        assertEquals("test", this.user.getPassword());
    }

    @Test
    void should_return_Email() {
        assertEquals("test", this.user.getEmail());
    }

    @Test
    void should_return_FirstName() {
        assertEquals("test", this.user.getFirstName());
    }

    @Test
    void should_return_LastName() {
        assertEquals("test", this.user.getLastName());
    }

    @Test
    void should_return_Phone() {
        assertEquals("test", this.user.getPhone());
    }
}
