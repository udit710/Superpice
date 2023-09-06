package au.edu.rmit.sept.superprice.repository;

import au.edu.rmit.sept.superprice.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
