package org.example.serviceuserdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController


public class UserController {

    private final OrderClient orderClient;

    public UserController(OrderClient orderClient) {
        this.orderClient = orderClient;
    }


    @GetMapping("/users/ping")
    public String ping() {
        return "user-ok";
    }

    @GetMapping("/users/check-order")
    public String checkOrder() {
        return "user -> " + orderClient.ping(); // "user -> order-ok"
    }
}