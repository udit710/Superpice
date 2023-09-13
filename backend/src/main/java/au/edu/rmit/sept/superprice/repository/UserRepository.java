package au.edu.rmit.sept.superprice.repository;

import au.edu.rmit.sept.superprice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.userId = ?1")
    User findByUserId(Long userId);

    // @Query("SELECT u FROM App_User u WHERE u.username = ?1")
    // List<Order> findByUsername(String username);

    // @Query("SELECT u FROM App_User u WHERE u.email = ?1")
    // List<Order> findByEmail(String email);

    // @Query("SELECT u FROM App_User u WHERE u.phone = ?1")
    // List<Order> findByPhone(String phone);

    // @Query("SELECT u FROM App_User u WHERE u.addressId = ?1")
    // List<Order> findByAddressId(Long addressId);

    // @Query("SELECT u FROM App_User u WHERE u.firstName = ?1")
    // List<Order> findByFirstName(String firstName);

    // @Query("SELECT u FROM App_User u WHERE u.lastName = ?1")
    // List<Order> findByLastName(String lastName);

}
