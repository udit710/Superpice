package au.edu.rmit.sept.superprice.web;

import au.edu.rmit.sept.superprice.model.Address;
import au.edu.rmit.sept.superprice.model.CartItem;
import au.edu.rmit.sept.superprice.repository.AddressRepository;
import au.edu.rmit.sept.superprice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }
    @GetMapping("/{id}")
    public Address getAddressByAddressId(@PathVariable Long id) {
        return addressService.getAddressByAddressId(id);
    };

}
