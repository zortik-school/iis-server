package me.zort.iis.server.iisserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "app")
public class SpringAppConfig implements AppConfig {
    private List<String> allowedOrigins;
    private String jwtSecret;

}
