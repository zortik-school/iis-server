package me.zort.iis.server.iisserver.domain.campaign.exception;

public class InvalidActivityStateException extends RuntimeException {

    public InvalidActivityStateException() {
        super("Activity is in an invalid state for this operation.");
    }
}
