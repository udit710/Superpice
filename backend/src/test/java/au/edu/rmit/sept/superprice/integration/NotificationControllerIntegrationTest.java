package au.edu.rmit.sept.superprice.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import au.edu.rmit.sept.superprice.model.Notification;
import au.edu.rmit.sept.superprice.model.Notification.Type;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SuperPriceApplication.class)
public class NotificationControllerIntegrationTest {
    
    @Autowired
    MockMvc mvc;
    @Autowired
    Flyway flyway;

    @BeforeEach
    private void init() {
        flyway.migrate();
    }

    @AfterEach
    private void clean() {
        flyway.clean();
    }

    @Test
    void get_all_notifications() throws Exception {
        mvc.perform(get("/api/notifications").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

            .andExpect(jsonPath("$[0].notificationId", is(1)))
            .andExpect(jsonPath("$[0].message", is("Your order has been shipped!")));
    }

    @Test
    void get_notification_by_id_1() throws Exception {
        mvc.perform(get("/api/notifications/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

            .andExpect(jsonPath("$.notificationId", is(1)))
            .andExpect(jsonPath("$.message", is("Your order has been shipped!")));
    }

    @Test
    void create_notification() throws Exception {
        mvc.perform(post("/api/notifications")
            .content(ObjectToJson.asJsonString(new Notification(null, null, "test", Type.OFFERS)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void delete_notification() throws Exception {
        mvc.perform(delete("/api/notifications/1")).andExpect(status().isOk());
    }

}
