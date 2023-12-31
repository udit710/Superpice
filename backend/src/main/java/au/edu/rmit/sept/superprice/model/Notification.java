package au.edu.rmit.sept.superprice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.EnumType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "NOTIFICATION")
public class Notification {

    public Notification(Long notifId, User user, String message, Type type) {
        this.notificationId = notifId;
        this.user = user;
        this.message = message;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @JoinColumn(name = "message")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @Column(name = "date_sent")
    private Date dateSent;

    public static enum Type {
        OFFERS, ORDER, ERROR
    }
}
