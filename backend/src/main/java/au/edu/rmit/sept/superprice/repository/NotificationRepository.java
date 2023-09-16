package au.edu.rmit.sept.superprice.repository;

import au.edu.rmit.sept.superprice.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    // @Query("SELECT n FROM Notification n WHERE n.notification_id = ?1")
    // Notification findByNotificationId(Integer Id);

    @Query("SELECT n FROM Notification n WHERE n.notification_id = :notificationId")
    Notification findByNotificationId(@Param("notificationId") Integer notificationId);


    @Modifying
    @Query("UPDATE Notification n SET n.seen = TRUE WHERE n.notification_id = :notificationId")
    Notification notificationSeen(@Param("notificationId") Integer id);
}