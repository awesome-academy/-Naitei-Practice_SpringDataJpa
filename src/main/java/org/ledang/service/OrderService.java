package org.ledang.service;

import org.ledang.entity.Order;

import java.util.List;

public interface OrderService {
    Order addOrder(Order order, int userId);
    Order getOrderById(int id);
    List<Order> getUserOrders(int userId);
    Order updateOrder(Order order, int orderId);
}
