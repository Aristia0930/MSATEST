package org.example.serviceuserdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/users/ping")
    public String ping() {
        return "user-ok";
    }
}