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
     * The type of operation this filter can process.
     *
     * @return the class of the operation type T
     */
    Class<T> getOperationType();
}
