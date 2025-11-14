package me.zort.iis.server.iisserver.config;

import java.util.List;

public interface AppConfig {

    /**
     * Gets the list of allowed origins for CORS configuration.
     *
     * @return a list of allowed origin URLs
     */
    List<String> getAllowedOrigins();

    /**
     * Gets the JWT secret key used for token generation and validation.
     *
     * @return the JWT secret key as a string
     */
    String getJwtSecret();
}
