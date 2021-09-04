package ru.ponies.pink.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ponies.pink.configuration.properties.MetricProperties;
import ru.ponies.pink.configuration.properties.ShopProperties;

@Configuration
@EnableConfigurationProperties({MetricProperties.class, ShopProperties.class})
public class ApplicationConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
