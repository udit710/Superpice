package au.edu.rmit.sept.superprice.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Address;
import au.edu.rmit.sept.superprice.service.AddressService;
import au.edu.rmit.sept.superprice.web.AddressController;

@SpringBootTest
public class AddressControllerTest {

    AddressController addressController;
    AddressService addressService;

    @BeforeEach
    void init() {
        this.addressService = mock(AddressService.class);
        this.addressController = new AddressController(this.addressService);
    }

    @Test
    void should_get_all_addresses() {
        when(this.addressService.getAll())
            .thenReturn(List.of(new Address(), new Address()));
        
        assertEquals(2, this.addressController.getAll().size());
    }

    @Test
    void should_return_product_by_id() {
        Address address = new Address();
        when(this.addressService.getById(1l))
            .thenReturn(address);
        
        assertEquals(address, this.addressController.getAddressById(1l));
    }

    @Test
    void should_return_null_when_no_address_by_id() {
        when(this.addressService.getById(11l))
            .thenReturn(null);
        
        assertNull(this.addressController.getAddressById(11l));
    }

    @Test
    void should_create_address() {
        Address address = new Address();
        when(this.addressService.addAddress(address))
            .then(returnsFirstArg());

        assertEquals(address, this.addressController.addAddress(address));
    }

    @Test
    void should_update_address() {
        Address address = new Address();
        when(this.addressService.addAddress(address))
            .then(returnsFirstArg());

        assertEquals(address, this.addressController.updateAddress(1l, address));
    }
}
