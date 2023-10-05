package au.edu.rmit.sept.superprice.service;

import au.edu.rmit.sept.superprice.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import au.edu.rmit.sept.superprice.repository.NotificationRepository;

import java.util.List;

@Service
public class NotificationService {

    private NotificationRepository notificationRepository;
    
    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(Long Id) {
        return notificationRepository.findById(Id).orElse(null);
    }


    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    // public Notification updateNotification(Integer id) {
    //     return notificationRepository.notificationSeen(id);
    // }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
