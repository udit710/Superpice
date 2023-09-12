package au.edu.rmit.sept.superprice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import au.edu.rmit.sept.superprice.model.Order;
import au.edu.rmit.sept.superprice.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

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

    // @GetMapping("/customer/{customerId}")
    // public List<Order> getOrdersByCustomerId(@PathVariable Long customerId) {
    //     return orderService.getOrdersByCustomerId(customerId);
    // }

    // @GetMapping("/status/{status}")
    // public List<Order> getOrdersByStatus(@PathVariable Order.OrderStatus status) {
    //     return orderService.getOrdersByStatus(status);
    // }

    // @GetMapping("/lastUpdated/{lastUpdated}")
    // public List<Order> getOrdersByLastUpdated(@PathVariable String lastUpdated) {
    //     return orderService.getOrdersByLastUpdated(lastUpdated);
    // }

    // @GetMapping("/customer/{customerId}/status/{status}")
    // public List<Order> getOrdersByCustomerIdAndStatus(@PathVariable Long customerId, @PathVariable Order.OrderStatus status) {
    //     return orderService.getOrdersByCustomerIdAndStatus(customerId, status);
    // }

    // @GetMapping("/customer/{customerId}/lastUpdated/{lastUpdated}")
    // public List<Order> getOrdersByCustomerIdAndLastUpdated(@PathVariable Long customerId, @PathVariable String lastUpdated) {
    //     return orderService.getOrdersByCustomerIdAndLastUpdated(customerId, lastUpdated);
    // }

    // @GetMapping("/status/{status}/lastUpdated/{lastUpdated}")
    // public List<Order> getOrdersByStatusAndLastUpdated(@PathVariable Order.OrderStatus status, @PathVariable String lastUpdated) {
    //     return orderService.getOrdersByStatusAndLastUpdated(status, lastUpdated);
    // }

    // @GetMapping("/customer/{customerId}/status/{status}/lastUpdated/{lastUpdated}")
    // public List<Order> getOrdersByCustomerIdAndStatusAndLastUpdated(@PathVariable Long customerId, @PathVariable Order.OrderStatus status, @PathVariable String lastUpdated) {
    //     return orderService.getOrdersByCustomerIdAndStatusAndLastUpdated(customerId, status, lastUpdated);
    // }

    // @GetMapping("/customer/{customerId}/address/{addressId}")
    // public List<Order> getOrdersByCustomerIdAndAddressId(@PathVariable Long customerId, @PathVariable Long addressId) {
    //     return orderService.getOrdersByCustomerIdAndAddressId(customerId, addressId);
    // }
}
