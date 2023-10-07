package au.edu.rmit.sept.superprice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @ManyToOne
    @JoinColumn(name = "product_details_id", referencedColumnName = "product_details_id")
    private ProductDetails productDetailsId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User userId;

    @Column(name = "quantity")
    private int quantity;
    
}