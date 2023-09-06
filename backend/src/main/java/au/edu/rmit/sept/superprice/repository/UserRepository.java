package au.edu.rmit.sept.superprice.repository;

import au.edu.rmit.sept.superprice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
