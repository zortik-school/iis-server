package me.zort.iis.server.iisserver.cqrs;

import me.zort.iis.server.iisserver.cqrs.exception.UnhandledOperationException;

public interface OperationExecutor {

    /**
     * Dispatches the given operation and returns the result.
     *
     * @param operation the operation to be executed
     * @return the result of the operation
     * @param <R> the type of result returned by the operation
     * @throws UnhandledOperationException if no handler is found for the operation
     * @throws RuntimeException for other execution errors
     */
    <R> R dispatch(Operation<R> operation);
}
