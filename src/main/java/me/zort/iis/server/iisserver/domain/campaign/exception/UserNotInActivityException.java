package me.zort.iis.server.iisserver.domain.campaign.exception;

public class UserNotInActivityException extends RuntimeException {

    public UserNotInActivityException() {
        super("User not in activity.");
    }
}
