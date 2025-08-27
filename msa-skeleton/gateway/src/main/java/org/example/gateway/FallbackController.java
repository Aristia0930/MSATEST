package org.example.gateway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    @GetMapping("/__fallback")
    public ResponseEntity<String> fb() {
        return ResponseEntity.status(503).body("gateway-fallback");
    }
}
