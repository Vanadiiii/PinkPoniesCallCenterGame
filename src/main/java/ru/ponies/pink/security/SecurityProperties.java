package ru.ponies.pink.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application.security")
public class SecurityProperties {
    private String secretKey;
    private int expiredAtInMinutes;
    private List<String> allowedEndpoints;
    private String loginEndpoint;
 
    public String[] getAllowedEndpoints() {
        return allowedEndpoints
                .toArray(new String[]{});
    }
}
