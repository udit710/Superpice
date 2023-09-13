package au.edu.rmit.sept.superprice.repository;

import au.edu.rmit.sept.superprice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM APP_USER", nativeQuery = true)
    List<User> findAll();

    @Query("SELECT u FROM User u WHERE u.userId = ?1")
    User findByUserId(Long userId);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    List<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    List<User> findByEmail(String email);
}
