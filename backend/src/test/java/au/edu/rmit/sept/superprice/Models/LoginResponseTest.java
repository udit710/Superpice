package au.edu.rmit.sept.superprice.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import au.edu.rmit.sept.superprice.auth.LoginResponse;

@SpringBootTest
public class LoginResponseTest {
    
    LoginResponse loginResponse;

    @BeforeEach
    void createResponse() {
        this.loginResponse = new LoginResponse("test", "test", HttpStatus.OK, "test");
    }

    @Test
    void should_return_email() {
        assertEquals("test", this.loginResponse.getEmail());
    }

    @Test
    void should_return_token() {
        assertEquals("test", this.loginResponse.getToken());
    }

    @Test
    void should_return_message() {
        assertEquals("test", this.loginResponse.getMessage());
    }

    @Test
    void should_return_status() {
        assertEquals(HttpStatus.OK, this.loginResponse.getHttpStatus());
    }

    @Test
    void test_twoArg_constructor() {
        LoginResponse lr = new LoginResponse(HttpStatus.NOT_FOUND, "testing");
        assertEquals("testing", lr.getMessage());
    }

    @Test
    void test_threeArg_constructor() {
        LoginResponse lr = new LoginResponse("testing", "testing", HttpStatus.NOT_FOUND);
        assertEquals("testing", lr.getToken());
    }
}
