package me.zort.iis.server.iisserver.cqrs.exception;

import lombok.Getter;

@Getter
public class ExceptionInHandlerException extends RuntimeException {
    private final Exception exception;

    public ExceptionInHandlerException(Exception exception) {
        super(exception);
        this.exception = exception;
    }
}
