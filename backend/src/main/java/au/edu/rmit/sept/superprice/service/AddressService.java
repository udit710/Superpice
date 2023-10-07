package au.edu.rmit.sept.superprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.rmit.sept.superprice.model.Address;
import au.edu.rmit.sept.superprice.repository.AddressRepository;

@Service
public class AddressService {
    
    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address getById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }
    
}
