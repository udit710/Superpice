package au.edu.rmit.sept.superprice.Controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import au.edu.rmit.sept.superprice.model.Order.OrderStatus;
import au.edu.rmit.sept.superprice.model.Order.PaymentMethod;
import au.edu.rmit.sept.superprice.model.Order;
import au.edu.rmit.sept.superprice.service.OrderService;
import au.edu.rmit.sept.superprice.web.OrderController;

@SpringBootTest
public class OrderControllerTest {

    OrderController orderController;
    OrderService orderService;

    @BeforeEach
    void initializeObjects() {
        this.orderService = mock(OrderService.class);
        this.orderController = new OrderController(this.orderService);
    }

    @Test
    void should_return_all_orders() {
        Order order = new Order(1l, 1l, Date.valueOf("2023-09-12"), OrderStatus.DELIVERED, 8.0, 3l, PaymentMethod.PAYPAL);
        when(this.orderService.getAllOrders())
            .thenReturn(List.of(order));

        assertEquals(1, this.orderController.getAllOrders().size());
    }

    @Test
    void should_return_empty_list_when_no_orders() {
        when(this.orderService.getAllOrders()).thenReturn(new ArrayList<Order>());

        assertEquals(0, this.orderController.getAllOrders().size());
    }

    @Test
    void should_return_all_orders_for_a_single_user() {
        when(this.orderService.getOrdersByUserId(4l))
            .thenReturn(List.of(
                new Order(4l, 4l, Date.valueOf("2023-09-12"), OrderStatus.CANCELLED, 5.0, 3l, PaymentMethod.DEBIT_CARD),
                new Order(5l, 4l, Date.valueOf("2023-09-12"), OrderStatus.DELIVERED, 9.0, 3l, PaymentMethod.WALLET)
            ));
        
        assertEquals(2, this.orderController.getOrdersByUserId(4l).size());
    }

    @Test
    void should_return_empty_list_when_no_orders_for_a_single_user() {
        when(this.orderService.getOrdersByUserId(4l)).thenReturn(new ArrayList<Order>());

        assertEquals(0, this.orderController.getOrdersByUserId(4l).size());
    }

    @Test
    void should_return_all_orders_for_a_single_status() {
        when(this.orderService.getOrdersByStatus(OrderStatus.DELIVERED))
            .thenReturn(List.of(
                new Order(4l, 4l, Date.valueOf("2023-09-12"), OrderStatus.DELIVERED, 5.0, 3l, PaymentMethod.DEBIT_CARD),
                new Order(5l, 4l, Date.valueOf("2023-09-12"), OrderStatus.DELIVERED, 9.0, 3l, PaymentMethod.WALLET)
            ));
        
        assertEquals(2, this.orderController.getOrdersByStatus(OrderStatus.DELIVERED).size());
    }

    @Test
    void should_return_empty_list_when_no_orders_for_a_single_status() {
        when(this.orderService.getOrdersByStatus(OrderStatus.DELIVERED)).thenReturn(new ArrayList<Order>());

        assertEquals(0, this.orderController.getOrdersByStatus(OrderStatus.DELIVERED).size());
    }

    @Test
    void should_return_all_orders_for_a_single_payment_method() {
        when(this.orderService.getOrdersByPaymentMethod(PaymentMethod.DEBIT_CARD))
            .thenReturn(List.of(
                new Order(4l, 4l, Date.valueOf("2023-09-12"), OrderStatus.DELIVERED, 5.0, 3l, PaymentMethod.DEBIT_CARD),
                new Order(5l, 4l, Date.valueOf("2023-09-12"), OrderStatus.DELIVERED, 9.0, 3l, PaymentMethod.DEBIT_CARD)
            ));
        
        assertEquals(2, this.orderController.getOrdersByPaymentMethod(PaymentMethod.DEBIT_CARD).size());
    }

    @Test
    void should_return_empty_list_when_no_orders_for_a_single_payment_method() {
        when(this.orderService.getOrdersByPaymentMethod(PaymentMethod.DEBIT_CARD)).thenReturn(new ArrayList<Order>());

        assertEquals(0, this.orderController.getOrdersByPaymentMethod(PaymentMethod.DEBIT_CARD).size());
    }

    @Test
    void should_call_delete_order_method() {
        this.orderController.deleteOrder(1l);
        verify(this.orderService, times(1))
            .deleteOrder(1l);
    }

    @Test
    void should_call_saveOrUpdateOrder_method_when_updating() {
        Order order = new Order();
        this.orderController.updateOrder(1l, order);
        verify(this.orderService, times(1))
            .saveOrUpdateOrder(order);
    }

    @Test
    void should_return_same_object_when_create_new_order() {
        Order order = new Order();

        when(this.orderService.saveOrUpdateOrder(order))
            .then(returnsFirstArg());
        
        assertEquals(order, this.orderController.createOrder(order));
    }

    @Test
    void should_return_order_by_id() {
        when(this.orderService.getOrderById(1l))
            .thenReturn(new Order(1l, 1l, new Date(0), null, 1.0, 1l, PaymentMethod.CREDIT_CARD));
        
        assertNotNull(this.orderController.getOrderById(1l));
    }

    @Test
    void should_return_null_when_id_does_not_exist_in_order_by_id() {
        when(this.orderService.getOrderById(11l))
            .thenReturn(null);
        
        assertNull(this.orderController.getOrderById(11l));
    }
    
}
