package au.edu.rmit.sept.superprice.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import au.edu.rmit.sept.superprice.SuperPriceApplication;
import au.edu.rmit.sept.superprice.web.AuthController;
import au.edu.rmit.sept.superprice.auth.LoginResponse;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SuperPriceApplication.class)
public class AuthControllerIntegrationTest {
    
    @Autowired
    MockMvc mvc;
    @Autowired
    Flyway flyway;
    @Autowired
    AuthController authController;

    @BeforeEach
    private void init() {
        flyway.migrate();
    }

    @AfterEach
    private void clean() {
        flyway.clean();
    }

    @Test
    void signup() throws Exception {
        mvc.perform(post("/api/auth/sign-up")
            .content("{\"username\":\"test\", \"email\":\"test@test\", \"first_name\":\"test\", \"last_name\":\"test\", \"phone\":\"123456789\", \"password\":\"test\", \"addressId\":1}")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void login() throws Exception {
        // Create Account to Test Login With
        String object = "{\"username\":\"test\", \"email\":\"test@test\", \"first_name\":\"test\", \"last_name\":\"test\", \"phone\":\"123456789\", \"password\":\"test\", \"addressId\":1}";
        ObjectMapper mapper = new ObjectMapper();
        authController.signUp(mapper.readTree(object).deepCopy());

        mvc.perform(post("/api/auth/login")
            .content("{\"email\":\"test@test\", \"password\":\"test\"}")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void validate() throws Exception {
        // Create Account to Test With
        String object = "{\"username\":\"test\", \"email\":\"test@test\", \"first_name\":\"test\", \"last_name\":\"test\", \"phone\":\"123456789\", \"password\":\"test\", \"addressId\":1}";
        ObjectMapper mapper = new ObjectMapper();
        authController.signUp(mapper.readTree(object).deepCopy());

        // Login to get Token
        object = "{\"email\":\"test@test\", \"password\":\"test\"}";
        ResponseEntity<LoginResponse> res = authController.login(mapper.readTree(object).deepCopy());
        String token = res.getBody().getToken();

        mvc.perform(post("/api/auth/validate")
            .content("{\"token\":\"" + token + "\"}")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))

            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void signup_should_fail_if_username_exists() throws Exception{
        // Create Account to Test with Same Username
        String object = "{\"username\":\"test\", \"email\":\"test@test\", \"first_name\":\"test\", \"last_name\":\"test\", \"phone\":\"123456789\", \"password\":\"test\", \"addressId\":1}";
        ObjectMapper mapper = new ObjectMapper();
        authController.signUp(mapper.readTree(object).deepCopy());

        mvc.perform(post("/api/auth/sign-up")
            .content("{\"username\":\"test\", \"email\":\"newtest@test\", \"first_name\":\"test\", \"last_name\":\"test\", \"phone\":\"123456789\", \"password\":\"test\", \"addressId\":1}")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))            
            .andExpect(status().isBadRequest());
    }

    @Test
    void signup_should_fail_if_email_exists() throws Exception{
        // Create Account to Test with Same Email
        String object = "{\"username\":\"test\", \"email\":\"test@test\", \"first_name\":\"test\", \"last_name\":\"test\", \"phone\":\"123456789\", \"password\":\"test\", \"addressId\":1}";
        ObjectMapper mapper = new ObjectMapper();
        authController.signUp(mapper.readTree(object).deepCopy());

        mvc.perform(post("/api/auth/sign-up")
            .content("{\"username\":\"newtest\", \"email\":\"test@test\", \"first_name\":\"test\", \"last_name\":\"test\", \"phone\":\"123456789\", \"password\":\"test\", \"addressId\":1}")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))            
            .andExpect(status().isBadRequest());
    }

    @Test
    void login_should_fail_with_wrong_password() throws Exception {
        // Create Account to Test Login With
        String object = "{\"username\":\"test\", \"email\":\"test@test\", \"first_name\":\"test\", \"last_name\":\"test\", \"phone\":\"123456789\", \"password\":\"test\", \"addressId\":1}";
        ObjectMapper mapper = new ObjectMapper();
        authController.signUp(mapper.readTree(object).deepCopy());

        mvc.perform(post("/api/auth/login")
            .content("{\"email\":\"test@test\", \"password\":\"wrong\"}")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void login_should_fail_with_wrong_email() throws Exception {
        // Create Account to Test Login With
        String object = "{\"username\":\"test\", \"email\":\"test@test\", \"first_name\":\"test\", \"last_name\":\"test\", \"phone\":\"123456789\", \"password\":\"test\", \"addressId\":1}";
        ObjectMapper mapper = new ObjectMapper();
        authController.signUp(mapper.readTree(object).deepCopy());

        mvc.perform(post("/api/auth/login")
            .content("{\"email\":\"wrong@wrong\", \"password\":\"test\"}")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void validate_should_fail_with_wrong_token() throws Exception {
        // Create Account to Test With
        String object = "{\"username\":\"test\", \"email\":\"test@test\", \"first_name\":\"test\", \"last_name\":\"test\", \"phone\":\"123456789\", \"password\":\"test\", \"addressId\":1}";
        ObjectMapper mapper = new ObjectMapper();
        authController.signUp(mapper.readTree(object).deepCopy());

        // Login to get Token
        object = "{\"email\":\"test@test\", \"password\":\"test\"}";
        ResponseEntity<LoginResponse> res = authController.login(mapper.readTree(object).deepCopy());

        mvc.perform(post("/api/auth/validate")
            .content("{\"token\":\"wrong\"}")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void validate_should_fail_old_token() throws Exception {
        // Create Account to Test With
        String object = "{\"username\":\"test\", \"email\":\"test@test\", \"first_name\":\"test\", \"last_name\":\"test\", \"phone\":\"123456789\", \"password\":\"test\", \"addressId\":1}";
        ObjectMapper mapper = new ObjectMapper();
        authController.signUp(mapper.readTree(object).deepCopy());

        // Login to get Token
        object = "{\"email\":\"test@test\", \"password\":\"test\"}";
        ResponseEntity<LoginResponse> res = authController.login(mapper.readTree(object).deepCopy());
        String old_token = res.getBody().getToken();

        // Refresh the database
        this.flyway.clean();
        this.flyway.migrate();

        mvc.perform(post("/api/auth/validate")
            .content("{\"token\":\"" + old_token + "\"}")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }
}
