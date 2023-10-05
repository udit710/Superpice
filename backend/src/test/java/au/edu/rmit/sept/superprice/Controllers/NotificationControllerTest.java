package au.edu.rmit.sept.superprice.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Notification;
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
    Notification notification_list = new Notification(1L, 1L, "Test message",
    Type.OFFERS,
    Date.valueOf("2023-09-12"));
    when(this.notificationService.getAllNotifications())
    .thenReturn(List.of(notification_list));

    assertEquals(1, this.notificationController.getAllNotifications().size());
    }

    // @Test
    // void should_return_notification_with_id_one() {
    // Notification notif = new Notification(1, 1, "Test message", Type.OFFERS,
    // Date.valueOf("2023-09-12"));
    // when(this.notificationService.getNotificationById(1)).thenReturn(new
    // Notification());

    // assertEquals(1, this.notificationController.getNotificationById(1));
    // }

    // @Test
    // void should_return_notification_with_seen_true() {
    // Notification notif = new Notification(1, 1, "Test message", Type.OFFERS,
    // Date.valueOf("2023-09-12"));
    // when(this.notificationService.updateNotification(1)).thenReturn(new
    // Notification());

    // assertEquals(false,
    // this.notificationController.getNotificationById(1).seen());
    // }
}
