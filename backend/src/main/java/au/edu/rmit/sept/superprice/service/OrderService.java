package au.edu.rmit.sept.superprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import au.edu.rmit.sept.superprice.model.Order;
import au.edu.rmit.sept.superprice.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order saveOrUpdateOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    // public List<Order> getOrdersByCustomerId(Long customerId) {
    //     return orderRepository.findByCustomerId(customerId);
    // }

    // public List<Order> getOrdersByStatus(Order.OrderStatus status) {
    //     return orderRepository.findByStatus(status);
    // }

    // public List<Order> getOrdersByLastUpdated(String lastUpdated) {
    //     return orderRepository.findByLastUpdated(lastUpdated);
    // }

    // public List<Order> getOrdersByCustomerIdAndStatus(Long customerId, Order.OrderStatus status) {
    //     return orderRepository.findByCustomerIdAndStatus(customerId, status);
    // }

    // public List<Order> getOrdersByCustomerIdAndLastUpdated(Long customerId, String lastUpdated) {
    //     return orderRepository.findByCustomerIdAndLastUpdated(customerId, lastUpdated);
    // }

    // public List<Order> getOrdersByStatusAndLastUpdated(Order.OrderStatus status, String lastUpdated) {
    //     return orderRepository.findByStatusAndLastUpdated(status, lastUpdated);
    // }

    // public List<Order> getOrdersByCustomerIdAndStatusAndLastUpdated(Long customerId, Order.OrderStatus status, String lastUpdated) {
    //     return orderRepository.findByCustomerIdAndStatusAndLastUpdated(customerId, status, lastUpdated);
    // }

    // public List<Order> getOrdersByCustomerIdAndAddressId(Long customerId, Long addressId) {
    //     return orderRepository.findByCustomerIdAndAddressId(customerId, addressId);
    // }
}
