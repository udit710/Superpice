package au.edu.rmit.sept.superprice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDERS")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public enum OrderStatus {
        PENDING, IN_PROGRESS, DELIVERED, CANCELLED, SHIPPED
    }
    
    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "order_address_id")
    private Long orderAddressId;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    public enum PaymentMethod {
        CREDIT_CARD, PAYPAL, DEBIT_CARD, WALLET
    }
   
}
