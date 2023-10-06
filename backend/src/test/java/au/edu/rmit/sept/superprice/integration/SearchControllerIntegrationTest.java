package au.edu.rmit.sept.superprice.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class SearchControllerIntegrationTest {
    
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
    void get_all_products() throws Exception {
        mvc.perform(get("/api/search").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].productName", is("Skim Milk")));
    }

    @Test
    void get_products_by_name_milk() throws Exception {
        mvc.perform(get("/api/search?name=milk").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void get_products_by_description_milk() throws Exception {
        mvc.perform(get("/api/search?description=milk").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

}
