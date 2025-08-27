package org.example.serviceuserdemo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service-order")
public interface  OrderClient {
    @GetMapping("/orders/ping")
    String ping();
}
