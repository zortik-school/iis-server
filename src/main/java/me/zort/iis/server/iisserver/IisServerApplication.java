package me.zort.iis.server.iisserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        // User details config
        org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration.class
})
public class IisServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IisServerApplication.class, args);
    }
}
