package au.edu.rmit.sept.superprice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import au.edu.rmit.sept.superprice.model.Order;
import au.edu.rmit.sept.superprice.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.saveOrUpdateOrder(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        // Logic to update the order based on the provided ID
        return orderService.saveOrUpdateOrder(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/userId/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping("/status/{orderStatus}")
    public List<Order> getOrdersByStatus(@PathVariable Order.OrderStatus orderStatus) {
        return orderService.getOrdersByStatus(orderStatus);
    }

    @GetMapping("/paymentMethod/{paymentMethod}")
    public List<Order> getOrdersByPaymentMethod(@PathVariable Order.PaymentMethod paymentMethod) {
        return orderService.getOrdersByPaymentMethod(paymentMethod);
    }
}
