package me.zort.iis.server.iisserver.domain.campaign.exception;

public class UserAlreadyInActivityException extends RuntimeException {

    public UserAlreadyInActivityException() {
        super("User is already participating in this activity.");
    }
}
