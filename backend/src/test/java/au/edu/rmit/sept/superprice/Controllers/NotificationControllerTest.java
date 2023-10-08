package au.edu.rmit.sept.superprice.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Notification;
import au.edu.rmit.sept.superprice.model.User;
import au.edu.rmit.sept.superprice.model.Notification.Type;
import au.edu.rmit.sept.superprice.service.NotificationService;
import au.edu.rmit.sept.superprice.web.NotificationController;

@SpringBootTest
public class NotificationControllerTest {

    NotificationController notificationController;
    NotificationService notificationService;

    @BeforeEach
    void initializeObjects() {
        this.notificationService = mock(NotificationService.class);
        this.notificationController = new
        NotificationController(this.notificationService);
    }

    @Test
    void should_return_all_notifications() {
        User user = new User();
        Notification notification_list = new Notification(1L, user, "Test message",
        Type.OFFERS,
        Date.valueOf("2023-09-12"));
        when(this.notificationService.getAllNotifications())
        .thenReturn(List.of(notification_list));

        assertEquals(1, this.notificationController.getAllNotifications().size());
    }

    @Test
    void should_return_notification_with_id_one() {
        User user = new User();
        Notification notif = new Notification(1L, user, "Test message", Type.OFFERS);
        when(this.notificationService.getNotificationById(1L)).thenReturn(notif);

        assertEquals(notif, this.notificationController.getNotificationById(1L));
    }

    @Test
    void should_return_null_with_id_100() {
        when(this.notificationService.getNotificationById(100L)).thenReturn(null);

        assertNull(this.notificationController.getNotificationById(100L));
    }

    @Test
    void should_delete_notification() {
        this.notificationController.deleteNotification(1l);

        verify(this.notificationService, times(1))
            .deleteNotification(1l);
    }

    @Test
    void should_create_notification() {
        Notification notification = new Notification();
        this.notificationController.createNotification(notification);

        verify(this.notificationService, times(1))
            .saveNotification(notification);
    }

}
