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
public class SubCategoryControllerIntegrationTest {
    
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
    void get_Subcategory_by_id_1() throws Exception {
        mvc.perform(get("/api/subcategory/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

            .andExpect(jsonPath("$.subCategoryId", is(1)))
            .andExpect(jsonPath("$.subCategoryName", is("Almond Milk")));
    }
    
}
