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

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SuperPriceApplication.class)
public class ReviewControllerIntegrationTest {
    
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
    void get_all_reviews() throws Exception {
        mvc.perform(get("/api/reviews").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

            .andExpect(jsonPath("$[0].reviewId", is(1)))
            .andExpect(jsonPath("$[0].comment", is("Great milk!")));
    }

    @Test
    void get_review_by_id_1() throws Exception {
        mvc.perform(get("/api/reviews/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

            .andExpect(jsonPath("$.reviewId", is(1)))
            .andExpect(jsonPath("$.comment", is("Great milk!")));
    }

    @Test
    void create_review() throws Exception {
        mvc.perform(post("/api/reviews")
            .content("{\"productId\":1, \"userId\": 1, \"rating\":1, \"comment\":\"test comment\"}")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void update_review() throws Exception {
        mvc.perform(put("/api/reviews/1")
            .content("{\"comment\":\"test comment\"}")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

            .andExpect(jsonPath("$.comment", is("test comment")));
    }

    @Test
    void delete_review() throws Exception {
        mvc.perform(delete("/api/reviews/1")).andExpect(status().isOk());
    }

}
