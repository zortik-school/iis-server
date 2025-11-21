package me.zort.iis.server.iisserver.app.auth;

import me.zort.iis.server.iisserver.domain.auth.exception.InvalidCredentialsException;
import me.zort.iis.server.iisserver.domain.auth.exception.TokenInvalidException;
import me.zort.iis.server.iisserver.domain.user.User;
import me.zort.iis.server.iisserver.domain.user.exception.UserConflictException;

public interface AuthFacade {

    /**
     * Logs in a user with the provided credentials.
     *
     * @param args the login arguments
     * @return the JWT pair
     * @throws InvalidCredentialsException If the credentials are invalid
     */
    JwtPair login(LoginArgs args);

    /**
     * Registers a new user and returns a JWT pair.
     *
     * @param args the registration arguments
     * @return the JWT pair
     * @throws UserConflictException If a user with the same username already exists
     */
    JwtPair register(RegisterArgs args);

    /**
     * Refreshes session by given refresh token.
     *
     * @param refreshToken the refresh token
     * @return new JWT pair
     * @throws TokenInvalidException If the refresh token is invalid or expired
     */
    JwtPair refreshSession(String refreshToken);

    /**
     * Gets the logged-in user associated with the provided token.
     *
     * @param token the JWT token
     * @return the logged-in User
     * @throws TokenInvalidException If the token is invalid or expired
     */
    User getLoggedInUser(String token);

}
