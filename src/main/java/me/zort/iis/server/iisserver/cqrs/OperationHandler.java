package me.zort.iis.server.iisserver.cqrs;

/**
 * The OperationHandler interface for handling operations of type T that return results of type R.
 *
 * @param <T> the type of operation this handler processes
 * @param <R> the type of result returned by the operation
 */
public interface OperationHandler<T extends Operation<R>, R> {

    /**
     * Handles the given operation and returns the result.
     *
     * @param operation the operation to be handled
     * @return the result of the operation
     */
    R handle(T operation);

    /**
     * The type of operation this handler can process.
     *
     * @return the class of the operation type T
     */
    Class<T> getOperationType();
}
