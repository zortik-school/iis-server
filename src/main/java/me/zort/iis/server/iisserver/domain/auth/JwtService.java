package me.zort.iis.server.iisserver.domain.auth;

import me.zort.iis.server.iisserver.domain.auth.exception.TokenInvalidException;

public interface JwtService {

    /**
     * Generates a refresh token for the given user ID.
     *
     * @param userId ID of the user.
     * @return Generated refresh token.
     */
    String generateRefreshToken(long userId);

    /**
     * Generates an access token for the given user ID.
     *
     * @param userId ID of the user.
     * @return Generated access token.
     */
    String generateAccessToken(long userId);

    /**
     * Validates access token and returns user ID if valid, otherwise throws an exception.
     *
     * @param token Access token to validate.
     * @return User ID associated with the token.
     * @throws TokenInvalidException If the token is invalid.
     */
    long validateToken(String token);
}
