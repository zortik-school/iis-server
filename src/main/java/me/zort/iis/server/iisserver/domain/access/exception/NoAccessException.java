package me.zort.iis.server.iisserver.domain.access.exception;

public class NoAccessException extends RuntimeException {

    public NoAccessException(String message) {
        super(message);
    }
}
