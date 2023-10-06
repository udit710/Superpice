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

import com.fasterxml.jackson.databind.ObjectMapper;

import au.edu.rmit.sept.superprice.SuperPriceApplication;
import au.edu.rmit.sept.superprice.model.CartItem;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SuperPriceApplication.class)
public class CartItemControllerIntegrationTest {
    
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
    void get_all_cartItems() throws Exception {
        mvc.perform(get("/api/cartItems").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].quantity", is(2)));
    }

    @Test
    void get_cart_item_by_id_1() throws Exception {
        mvc.perform(get("/api/cartItems/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.quantity", is(2)));
    }

    @Test
    void create_cartItem() throws Exception {
        mvc.perform(post("/api/cartItems")
            .content("{\"userId\":1, \"productDetailsId\": 1, \"productId\":1, \"quantity\":1}")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void update_cartItem() throws Exception {
        mvc.perform(put("/api/cartItems/1")
            .content(asJsonString(new CartItem(null, null, null, null, 5)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

            .andExpect(jsonPath("$.quantity", is(5)));
    }

    @Test
    void delete_cartItem() throws Exception {
        mvc.perform(delete("/api/cartItems/1")).andExpect(status().isOk());
    }

    @Test
    void get_cart_item_by_productDetail_1() throws Exception {
        mvc.perform(get("/api/cartItems/productDetailsId/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }
    
    @Test
    void get_cart_item_by_userId_1() throws Exception {
        mvc.perform(get("/api/cartItems/userId/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void get_cart_item_by_quatity_2() throws Exception {
        mvc.perform(get("/api/cartItems/quantity/2").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].quantity", is(2)));
    }

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception err) {
            throw new RuntimeException(err);
        }
    }
}
