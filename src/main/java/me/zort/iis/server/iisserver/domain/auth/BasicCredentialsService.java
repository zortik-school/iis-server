package me.zort.iis.server.iisserver.domain.auth;

import me.zort.iis.server.iisserver.domain.auth.exception.InvalidCredentialsException;
import org.jetbrains.annotations.Nullable;

public interface BasicCredentialsService {

    /**
     * Validates password for user with given ID.
     *
     * @param password The password to validate.
     * @param passwordHash The stored password hash to validate against. Can be null.
     * @throws InvalidCredentialsException if the password is invalid.
     */
    void validatePassword(String password, @Nullable String passwordHash);

    /**
     * Hashes the given password.
     *
     * @param password The password to hash.
     * @return The hashed password.
     */
    String hashPassword(String password);
}
