package org.example.serviceauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthControler {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody String id){
        String token= jwtService.generateToken(id);

        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + token)  // 헤더에 넣을 수도 있고
                .body("로그인 성공");
    }
}
