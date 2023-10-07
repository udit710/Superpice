package au.edu.rmit.sept.superprice.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.CoreMatchers.is;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import au.edu.rmit.sept.superprice.SuperPriceApplication;
import au.edu.rmit.sept.superprice.model.Address.AddressType;
import au.edu.rmit.sept.superprice.web.UserController;
import au.edu.rmit.sept.superprice.model.Address;
import au.edu.rmit.sept.superprice.model.User;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SuperPriceApplication.class)
public class UserControllerIntegrationTest {
    
    @Autowired
    MockMvc mvc;
    @Autowired
    Flyway flyway;

    @Autowired
    UserController userController;

    @BeforeEach
    private void init() {
        flyway.migrate();
    }

    @AfterEach
    private void clean() {
        flyway.clean();
    }

    @Test
    void get_all_users() throws Exception {
        mvc.perform(get("/api/users").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

            .andExpect(jsonPath("$[0].userId", is(1)))
            .andExpect(jsonPath("$[0].username", is("john_doe")));
    }

    @Test
    void get_user_by_id_1() throws Exception {
        mvc.perform(get("/api/users/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

            .andExpect(jsonPath("$.userId", is(1)))
            .andExpect(jsonPath("$.username", is("john_doe")));
    }

    @Test
    void create_user() throws Exception {
        mvc.perform(post("/api/users")
            .content(ObjectToJson.asJsonString(new User(null, "test", "test", "test", "test", "test", "test", null)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void update_user() throws Exception {
        mvc.perform(put("/api/users/1")
            .content(ObjectToJson.asJsonString(new User(null, "test", "test", "test", "test", "test", "test", null)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    // @Test
    // void delete_user() throws Exception {
    //     mvc.perform(delete("/api/users/1")).andExpect(status().isOk());
    // }

    @Test
    void get_user_by_username() throws Exception {
        mvc.perform(get("/api/users/username/john_doe").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }
    
    @Test
    void get_user_by_email() throws Exception {
        mvc.perform(get("/api/users/email/john.doe@example.com").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void get_users_by_firstname() throws Exception {
        mvc.perform(get("/api/users/firstName/John").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void get_users_by_lastname() throws Exception {
        mvc.perform(get("/api/users/lastName/Doe").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void get_users_by_phone() throws Exception {
        mvc.perform(get("/api/users/phone/+1234567890").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void get_users_by_addressId() throws Exception {
        mvc.perform(get("/api/users/addressId/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void add_user_address() throws Exception {
        User user = userController.createUser(new User(null, "test", "test", "test", "test", "test", "test", null));

        mvc.perform(put("/api/users/address/" + user.getUserId())
            .content(ObjectToJson.asJsonString(new Address(null, "test", null, "test", "test", "test", "test", AddressType.USER)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void update_user_address() throws Exception {
        mvc.perform(put("/api/users/address/1")
            .content(ObjectToJson.asJsonString(new Address(null, "test", null, "test", "test", "test", "test", AddressType.USER)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

}
