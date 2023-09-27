package au.edu.rmit.sept.superprice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import .User;
import .ProductDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;


// 
// CREATE TABLE IF NOT EXISTS cart_item (
//     cart_item_id INT AUTO_INCREMENT PRIMARY KEY,
//     user_id INT,
//     product_details_id INT,
//     quantity INT NOT NULL,
//     FOREIGN KEY (user_id) REFERENCES app_user(user_id),
//     FOREIGN KEY (product_details_id) REFERENCES product_details(product_details_id)
// );
// 
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CART_ITEM")
public class CartItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_details_id", referencedColumnName = "product_details_id")
    private ProductDetails product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "quantity")
    private int quantity;
    
}