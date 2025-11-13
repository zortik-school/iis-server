package me.zort.iis.server.iisserver.cqrs.exception;

import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Operation;

@Getter
public class UnhandledOperationException extends RuntimeException {
    private final Operation<?> operation;

    public UnhandledOperationException(Operation<?> operation) {
        this.operation = operation;
    }
}
