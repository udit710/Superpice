package au.edu.rmit.sept.superprice.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

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
import au.edu.rmit.sept.superprice.model.Order;
import au.edu.rmit.sept.superprice.model.Order.OrderStatus;
import au.edu.rmit.sept.superprice.model.Order.PaymentMethod;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SuperPriceApplication.class)
public class OrderControllerIntegrationTest {
    
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
    void get_all_orders() throws Exception {
        mvc.perform(get("/api/orders").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].paymentMethod", is("CREDIT_CARD")));
    }

    @Test
    void get_order_id_2() throws Exception {
        mvc.perform(get("/api/orders/2").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

            .andExpect(jsonPath("$.id", is(2)))
            .andExpect(jsonPath("$.paymentMethod", is("CREDIT_CARD")));
    }

    @Test
    void get_orders_by_userId_1() throws Exception {
        mvc.perform(get("/api/orders/userId/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].paymentMethod", is("CREDIT_CARD")));
    }

    @Test
    void get_orders_by_status_pending() throws Exception {
        mvc.perform(get("/api/orders/status/PENDING").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].paymentMethod", is("CREDIT_CARD")));
    }

    @Test
    void get_orders_by_paymentMethod_debitCard() throws Exception {
        mvc.perform(get("/api/orders/paymentMethod/DEBIT_CARD").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

            .andExpect(jsonPath("$[0].id", is(3)))
            .andExpect(jsonPath("$[0].paymentMethod", is("DEBIT_CARD")));
    }

    @Test
    void create_order() throws Exception {
        mvc.perform(post("/api/orders")
            .content(ObjectToJson.asJsonString(new Order(null, null, new Date(0), OrderStatus.PENDING, 1d, null, PaymentMethod.CREDIT_CARD)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void update_order_1() throws Exception {
        mvc.perform(put("/api/orders/1")
            .content(ObjectToJson.asJsonString(new Order(1l, null, new Date(0), OrderStatus.PENDING, 1d, null, PaymentMethod.CREDIT_CARD)))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    // @Test
    // void delete_order() throws Exception {
    //     mvc.perform(delete("/api/orders/2")).andExpect(status().isOk());
    // }
    
}
