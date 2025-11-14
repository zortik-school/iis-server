package me.zort.iis.server.iisserver.cqrs;

import java.util.function.Supplier;

public interface OperationFilter<T extends Operation<R>, R> {

    /**
     * Filters the given operation and provides the result to the consumer,
     * if applicable, or else allows normal processing to continue.
     *
     * @param operation the operation to be filtered
     * @param next a supplier that provides the next step in processing
     */
    R filter(T operation, Supplier<R> next);

    /**
     * Determines if this filter can handle the given operation.
     *
     * @param operation the operation to check
     * @return true if this filter can handle the operation, false otherwise
     */
    default boolean handles(Operation<?> operation) {
        return getOperationType().isAssignableFrom(operation.getClass());
    }

    /**
     * The type of operation this filter can process.
     *
     * @return the class of the operation type T
     */
    Class<T> getOperationType();
}
