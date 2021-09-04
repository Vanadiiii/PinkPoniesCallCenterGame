package ru.ponies.pink.configuration;

import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.ponies.pink.client.MetricClient;
import ru.ponies.pink.client.ShopClient;
import ru.ponies.pink.configuration.properties.MetricProperties;
import ru.ponies.pink.configuration.properties.ShopProperties;

@Configuration
@Import({FeignClientsConfiguration.class})
public class FeignClientConfig {

    @Bean
    public ShopClient shopClient(ShopProperties properties, Decoder decoder, Encoder encoder) {
        return Feign.builder()
                .contract(new SpringMvcContract())
                .logger(new Slf4jLogger(ShopClient.class))
                .logLevel(Logger.Level.FULL)
                .encoder(encoder)
                .decoder(decoder)
                .target(ShopClient.class, properties.getUrl());
    }

    @Bean
    public MetricClient metricClient(MetricProperties properties, Decoder decoder, Encoder encoder) {
        return Feign.builder()
                .contract(new SpringMvcContract())
                .logger(new Slf4jLogger(MetricClient.class))
                .logLevel(Logger.Level.FULL)
                .encoder(encoder)
                .decoder(decoder)
                .target(MetricClient.class, properties.getUrl());
    }
}
