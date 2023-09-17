package au.edu.rmit.sept.superprice.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Address;
import au.edu.rmit.sept.superprice.model.Address.AddressType;

@SpringBootTest
public class AddressTest {
    
    Address address;

    @BeforeEach
    void createReview() {
        this.address = new Address(1l, "test", "test", "test", "test", "test", "test", AddressType.USER);
    }

    @Test
    void should_return_id() {
        assertEquals(1l, this.address.getId());
    }

    @Test
    void should_return_AddressLine1() {
        assertEquals("test", this.address.getAddressLine1());
    }

    @Test
    void should_return_AddressLine2() {
        assertEquals("test", this.address.getAddressLine2());
    }

    @Test
    void should_return_City() {
        assertEquals("test", this.address.getCity());
    }

    @Test
    void should_return_State() {
        assertEquals("test", this.address.getState());
    }

    @Test
    void should_return_PostalCode() {
        assertEquals("test", this.address.getPostalCode());
    }

    @Test
    void should_return_Country() {
        assertEquals("test", this.address.getCountry());
    }

    @Test
    void should_return_address_type() {
        assertEquals(AddressType.USER, this.address.getAddressType());
    }

}
