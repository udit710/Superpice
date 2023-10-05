package au.edu.rmit.sept.superprice.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Notification;
import au.edu.rmit.sept.superprice.model.Notification.Type;
import au.edu.rmit.sept.superprice.model.User;
import au.edu.rmit.sept.superprice.repository.NotificationRepository;
import au.edu.rmit.sept.superprice.service.NotificationService;

@SpringBootTest
public class NotificationServiceTest {
    
    NotificationService notificationService;
    NotificationRepository notificationRepository;

    @BeforeEach
    void initializeObjects() {
        this.notificationRepository = mock(NotificationRepository.class);
        this.notificationService = new NotificationService(this.notificationRepository);
    }

    @Test
    void should_return_all_notifications() {
        User user = new User();
        Notification notification_list = new Notification(1L, user, "Test message",
        Type.OFFERS,
        Date.valueOf("2023-09-12"));
        when(this.notificationRepository.findAll())
        .thenReturn(List.of(notification_list));

        assertEquals(1, this.notificationService.getAllNotifications().size());
    }

    @Test
    void should_return_notification_with_id_one() {
        User user = new User();
        Notification notif = new Notification(1L, user, "Test message", Type.OFFERS);
        when(this.notificationRepository.findById(1L)).thenReturn(Optional.of(notif));

        assertEquals(notif, this.notificationService.getNotificationById(1L));
    }

    @Test
    void should_return_null_with_id_100() {
        when(this.notificationRepository.findById(100L)).thenReturn(Optional.empty());

        assertNull(this.notificationService.getNotificationById(100L));
    }

    @Test
    void should_delete_notification() {
        this.notificationService.deleteNotification(1l);

        verify(this.notificationRepository, times(1))
            .deleteById(1l);
    }

    @Test
    void should_create_notification() {
        Notification notification = new Notification();
        this.notificationService.saveNotification(notification);

        verify(this.notificationRepository, times(1))
            .save(notification);
    }

}
