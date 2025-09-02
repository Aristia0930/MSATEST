package org.example.commonsecuritystarter;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@AutoConfiguration
@EnableMethodSecurity
@EnableConfigurationProperties(CommonSecurityProps.class)
@ConditionalOnProperty(value = "common.security.enabled", matchIfMissing = true)
@ConditionalOnClass(SecurityFilterChain.class)
public class CommonSecurityAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(SecurityFilterChain.class)
    SecurityFilterChain securityFilterChain(HttpSecurity http, CommonSecurityProps props) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(auth -> {
            for (String p : props.permitPaths()) auth.requestMatchers(p).permitAll();
            auth.anyRequest().authenticated();
        });
        // 🔁 변경 포인트: 인자 없는 jwt() → jwt(Customizer.withDefaults())
        http.oauth2ResourceServer(o -> o.jwt(Customizer.withDefaults()));
        return http.build();
    }

    @Bean
    @ConditionalOnProperty("common.security.hmac-secret")
    JwtDecoder hmacJwtDecoder(CommonSecurityProps props) {
        byte[] keyBytes = Base64.getDecoder().decode(props.hmacSecret());  // ★ Base64 디코드
        SecretKey key = new SecretKeySpec(keyBytes, "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(key).macAlgorithm(MacAlgorithm.HS256).build();
    }

}