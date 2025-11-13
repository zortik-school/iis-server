package me.zort.iis.server.iisserver.cqrs;

import java.util.function.Consumer;

public interface OperationFilter<T extends Operation<R>, R> {

    /**
     * Filters the given operation and provides the result to the consumer,
     * if applicable, or else allows normal processing to continue.
     *
     * @param operation the operation to be filtered
     * @param provisionallyReturnResult a consumer to accept the result if the filter decides to provide one
     */
    void filter(T operation, Consumer<R> provisionallyReturnResult);

    /**
     * The type of operation this filter can process.
     *
     * @return the class of the operation type T
     */
    @SuppressWarnings("unchecked") // Safe cast based on type check
    default Class<T> getOperationType() {
        // Workaround
        return (Class<T>) Operation.class;
    }
}
