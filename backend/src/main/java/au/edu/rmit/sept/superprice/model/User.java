package au.edu.rmit.sept.superprice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "APP_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Setter
    @Column(name = "username", nullable = false, unique=true)
    private String username;

    @Setter
    @Column(name = "password", nullable = false)
    private String password;
    
    @Setter
    @Column(name = "email", nullable = false, unique=true)
    private String email;

    @Setter
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Setter
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Setter
    @Column(name = "phone")
    private String phone;

    @Setter
    @Column(name = "address_id")
    private Long addressId;

    public User(String email) {
        this.email = email;
    }

    public User(String username, String password, String email, String firstName, String lastName, String phone, Long addressId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.addressId = addressId;
    }
}
