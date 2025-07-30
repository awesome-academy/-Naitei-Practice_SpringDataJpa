package org.ledang.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ledang.entity.Order;
import org.ledang.entity.User;
import org.ledang.repository.OrderRepository;
import org.ledang.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    final private OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Transactional
    public Order addOrder(Order order, int userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Order newOrder = new Order();
            newOrder.setUser(user);
            newOrder.setPrice(order.getPrice());
            return orderRepository.save(newOrder);

        } catch (RuntimeException e) {
            System.out.println("Error in addOrder: " + e.getMessage());
            return null;
        }
    }

    @Transactional
    public Order getOrderById(int id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Transactional
    public List<Order> getUserOrders(int userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Transactional
    public Order updateOrder(Order order, int orderId) {
        try {
            Order orderUpdate = orderRepository.findById(orderId)
                    .orElseThrow(() -> new RuntimeException("Order not found"));
            orderUpdate.setPrice(order.getPrice());
            return orderRepository.save(orderUpdate);
        } catch (RuntimeException e) {
            System.out.println("Error in updateOrder: " + e.getMessage());
            return null;
        }
    }
}
