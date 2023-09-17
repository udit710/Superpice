package au.edu.rmit.sept.superprice.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Address;
import au.edu.rmit.sept.superprice.model.Store;

@SpringBootTest
public class StoreTest {
    
    Store store;

    @BeforeEach
    void createStore() {
        this.store = new Store(1l, "test", new Address(), "test");
    }

    @Test
    void should_return_id() {
        assertEquals(1l, this.store.getId());
    }

    @Test
    void should_return_StoreName() {
        assertEquals("test", this.store.getStoreName());
    }

    @Test
    void should_return_ContactDetails() {
        assertEquals("test", this.store.getContactDetails());
    }

    @Test
    void should_return_address() {
        assertNotNull(this.store.getAddress());
    }

}
