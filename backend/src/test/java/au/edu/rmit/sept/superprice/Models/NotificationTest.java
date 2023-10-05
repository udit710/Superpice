package au.edu.rmit.sept.superprice.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Notification;
import au.edu.rmit.sept.superprice.model.Product;
import au.edu.rmit.sept.superprice.model.User;

@SpringBootTest
public class NotificationTest {
    
    Notification notification;

    @BeforeEach
    void createNotification() {
        this.notification = new Notification(1l, new User(), "test", Notification.Type.OFFERS, new Date(0));
    }

    @Test
    void should_return_id() {
        assertEquals(1l, this.notification.getNotificationId());
    }

    @Test
    void should_return_() {
        assertNotNull(this.notification.getUser());
    }

    @Test
    void should_return_message() {
        assertEquals("test", this.notification.getMessage());
    }

    @Test
    void should_return_date() {
        assertNotNull(this.notification.getDateSent());
    }

    @Test 
    void should_return_type() {
        assertEquals(Notification.Type.OFFERS, this.notification.getType());
    }

}
