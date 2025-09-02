package org.example.commonsecuritystarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("common.security")
public record CommonSecurityProps(String[] permitPaths, Boolean enabled,String hmacSecret) {
    public String[] permitPaths() {
        return permitPaths != null ? permitPaths : new String[] { "/auth/**","/users/**" };
    }
    public Boolean enabled() { return enabled == null || enabled; }
}
