package org.example.serviceorder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @GetMapping("/orders/ping")
    public String ping() {
        return "order-ok";
    }

}
