package au.edu.rmit.sept.superprice.repository;

import au.edu.rmit.sept.superprice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository  extends JpaRepository<Address, Long> {

    @Query("SELECT u FROM Address u WHERE u.id = ?1")
    Address findByAddressId(Long id);
}
