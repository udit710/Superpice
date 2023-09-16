package au.edu.rmit.sept.superprice.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Order;
import au.edu.rmit.sept.superprice.model.Review;
import au.edu.rmit.sept.superprice.model.Order.OrderStatus;
import au.edu.rmit.sept.superprice.model.Order.PaymentMethod;
import au.edu.rmit.sept.superprice.repository.OrderRepository;
import au.edu.rmit.sept.superprice.service.OrderService;

@SpringBootTest
public class OrderServiceTest {
    
    OrderRepository orderRepository;
    OrderService orderService;

    @BeforeEach
    void init() {
        this.orderRepository = mock(OrderRepository.class);
        this.orderService = new OrderService(this.orderRepository);
    }

    @Test
    void should_get_all_orders() {
        when(this.orderRepository.findAll())
            .thenReturn(List.of(new Order()));
        
        assertEquals(1, this.orderService.getAllOrders().size());
    }

    @Test
    void should_return_empty_list_when_no_orders_exist() {
        when(this.orderRepository.findAll())
            .thenReturn(new ArrayList<Order>());
        
        assertEquals(0, this.orderService.getAllOrders().size());
    }

    @Test
    void should_return_order_by_id() {
        when(this.orderRepository.findById(1l))
            .thenReturn(Optional.of(new Order()));
        
        assertNotNull(this.orderService.getOrderById(1l));
    }

    @Test
    void should_return_null_when_no_order_with_id_exists() {
        when(this.orderRepository.findById(11l))
            .thenReturn(Optional.empty());
        
        assertNull(this.orderService.getOrderById(11l));
    }

    @Test
    void should_save_or_update_order() {
        Order order = new Order();
        when(this.orderRepository.save(order))
            .then(returnsFirstArg());

        assertEquals(order, this.orderService.saveOrUpdateOrder(order));
    }

    @Test
    void should_delete_order() {
        this.orderService.deleteOrder(1l);

        verify(this.orderRepository, times(1))
            .deleteById(1l);
    }

    @Test
    void should_return_orders_by_userId() {
        when(this.orderRepository.findByUserId(1l))
            .thenReturn(List.of(new Order()));
        
        assertEquals(1, this.orderService.getOrdersByUserId(1l).size());
    }

    @Test
    void should_return_empty_list_when_no_orders_exists_for_user() {
        when(this.orderRepository.findByUserId(1l))
            .thenReturn(new ArrayList<Order>());
        
        assertEquals(0, this.orderService.getOrdersByUserId(1l).size());
    }

    @Test
    void should_return_orders_by_status() {
        when(this.orderRepository.findByStatus(OrderStatus.DELIVERED))
            .thenReturn(List.of(new Order()));
        
        assertEquals(1, this.orderService.getOrdersByStatus(OrderStatus.DELIVERED).size());
    }

    @Test
    void should_return_empty_list_when_no_orders_exists_with_status() {
        when(this.orderRepository.findByStatus(OrderStatus.CANCELLED))
            .thenReturn(new ArrayList<Order>());
        
        assertEquals(0, this.orderService.getOrdersByStatus(OrderStatus.CANCELLED).size());
    }

    @Test
    void should_return_orders_by_paymentMethod() {
        when(this.orderRepository.findByPaymentMethod(PaymentMethod.CREDIT_CARD))
            .thenReturn(List.of(new Order()));
        
        assertEquals(1, this.orderService.getOrdersByPaymentMethod(PaymentMethod.CREDIT_CARD).size());
    }

    @Test
    void should_return_empty_list_when_no_orders_exists_with_paymentMethod() {
        when(this.orderRepository.findByPaymentMethod(PaymentMethod.PAYPAL))
            .thenReturn(new ArrayList<Order>());
        
        assertEquals(0, this.orderService.getOrdersByPaymentMethod(PaymentMethod.PAYPAL).size());
    }
    
}
