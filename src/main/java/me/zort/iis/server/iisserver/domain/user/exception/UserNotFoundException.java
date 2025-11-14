package me.zort.iis.server.iisserver.domain.user.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final long userId;

    public UserNotFoundException(long userId) {
        super("User with ID " + userId + " not found.");
        this.userId = userId;
    }
}
