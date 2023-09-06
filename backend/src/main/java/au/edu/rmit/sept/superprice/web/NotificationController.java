package au.edu.rmit.sept.superprice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import au.edu.rmit.sept.superprice.service.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // TODO: Add controller methods
}
