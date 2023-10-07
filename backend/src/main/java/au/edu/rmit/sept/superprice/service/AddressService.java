package au.edu.rmit.sept.superprice.service;

import au.edu.rmit.sept.superprice.model.Address;
import au.edu.rmit.sept.superprice.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService{

    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository){ this.addressRepository = addressRepository;};

    public Address getAddressByAddressId(Long id){return addressRepository.findByAddressId(id);};

}
