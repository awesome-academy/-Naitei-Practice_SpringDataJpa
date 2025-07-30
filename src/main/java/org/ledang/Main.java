package org.ledang;

import org.ledang.config.AppConfig;
import org.ledang.entity.Order;
import org.ledang.entity.User;
import org.ledang.service.OrderService;
import org.ledang.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        OrderService orderService = context.getBean(OrderService.class);

        User newUser = new User();
        newUser.setName("Le Quoc Dang");
        newUser.setAge(21);
        User userAdded = userService.addUser(newUser);
        System.out.println("Add user successfully: " + userAdded);

        userAdded.setAge(18);
        User userUpdated = userService.updateUser(userAdded, userAdded.getId());
        System.out.println("Update user successfully: " + userUpdated);

        userService.deleteUserById(userUpdated.getId());
        User userFind = userService.getUserById(userUpdated.getId());
        if (userFind == null) {
            System.out.println("User with ID " + userUpdated.getId() + " not found");
        }

        User user = userService.getUserById(2);
        Order orderAdd1 = orderService.addOrder(new Order(1000), user.getId());
        Order orderAdd2 = orderService.addOrder(new Order(2000), user.getId());
        System.out.println("Add order successfully: " + orderAdd1 + ", " + orderAdd2);

        orderAdd1.setPrice(5000);
        Order orderUpdated = orderService.updateOrder(orderAdd1, orderAdd1.getId());
        System.out.println("Update order successfully: " + orderUpdated);

        List<Order> orders = orderService.getUserOrders(user.getId());
        for (Order item : orders) {
            System.out.println(item);
        }
    }
}