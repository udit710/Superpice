package au.edu.rmit.sept.superprice.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Order;
import au.edu.rmit.sept.superprice.model.Order.OrderStatus;
import au.edu.rmit.sept.superprice.model.Order.PaymentMethod;

@SpringBootTest
public class OrderTest {
    
    Order order;

    @BeforeEach
    void createOrder() {
        this.order = new Order(1l, 1l, new Date(1), OrderStatus.DELIVERED, 1.0, 1l, PaymentMethod.CREDIT_CARD);
    }

    @Test
    void should_return_id() {
        assertEquals(1l, this.order.getId());
    }

    @Test
    void should_return_UserId() {
        assertEquals(1l, this.order.getUserId());
    }

    @Test
    void should_return_OrderAddressId() {
        assertEquals(1l, this.order.getOrderAddressId());
    }

    @Test
    void should_return_Date() {
        assertNotNull(this.order.getOrderDate());
    }

    @Test
    void should_return_orderStatus() {
        assertEquals(OrderStatus.DELIVERED, this.order.getOrderStatus());
    }

    @Test
    void should_return_paymentMethod() {
        assertEquals(PaymentMethod.CREDIT_CARD, this.order.getPaymentMethod());
    }

    @Test
    void should_return_TotalAmount() {
        assertEquals(1.0, this.order.getTotalAmount());
    }

}
