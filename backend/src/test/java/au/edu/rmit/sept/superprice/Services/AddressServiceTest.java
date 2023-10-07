package au.edu.rmit.sept.superprice.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Address;
import au.edu.rmit.sept.superprice.repository.AddressRepository;
import au.edu.rmit.sept.superprice.service.AddressService;

@SpringBootTest
public class AddressServiceTest {
    
    AddressService addressService;
    AddressRepository addressRepository;

    @BeforeEach
    void init() {
        this.addressRepository = mock(AddressRepository.class);
        this.addressService = new AddressService(this.addressRepository);
    }

    @Test
    void should_get_all_addresses() {
        when(this.addressRepository.findAll())
            .thenReturn(List.of(new Address()));
        
        assertEquals(1, this.addressService.getAll().size());
    }

    @Test
    void should_get_address_by_id() {
        when(this.addressRepository.findById(1l))
            .thenReturn(Optional.of(new Address()));
        
        assertNotNull(this.addressService.getById(1l));
    }

    @Test
    void should_return_null_when_no_review_with_id_exists() {
        when(this.addressRepository.findById(11l))
            .thenReturn(Optional.empty());
        
        assertNull(this.addressService.getById(11l));
    }

    @Test
    void should_save_address() {
        Address address = new Address();
        when(this.addressRepository.save(address))
            .then(returnsFirstArg());

        assertEquals(address, this.addressService.addAddress(address));
    }

}
