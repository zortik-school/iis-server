package me.zort.iis.server.iisserver.cqrs;

import me.zort.iis.server.iisserver.cqrs.exception.UnhandledOperationException;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Implementation of the OperationExecutor interface for dispatching operations to their respective handlers.
 *
 * @see OperationExecutor
 * @author ZorTik
 */
@Service
public class OperationExecutorImpl implements OperationExecutor {
    private final List<OperationHandler<?, ?>> handlers;
    private final List<OperationFilter<?, ?>> filters;

    /**
     * Constructs a new OperationExecutorImpl with the given handlers and filters.
     *
     * @param handlers the list of operation handlers
     * @param filters the list of operation filters
     */
    @Autowired
    public OperationExecutorImpl(List<OperationHandler<?, ?>> handlers, List<OperationFilter<?, ?>> filters) {
        this.handlers = List.copyOf(handlers);
        this.filters = List.copyOf(filters);
    }

    /**
     * @see OperationExecutor#dispatch(Operation)
     */
    @Override
    public <R> R dispatch(Operation<R> operation) {
        OperationHandler<Operation<R>, R> handler = getHandlerForOperation(operation)
                .orElseThrow(() -> new UnhandledOperationException(operation));

        List<OperationFilter<?, ?>> filters = new ArrayList<>(this.filters);
        Collections.reverse(filters);

        Supplier<R> nextFunc = () -> handler.handle(operation);
        for (OperationFilter<?, ?> filter : filters) {
            OperationFilter<Operation<R>, R> castedFilter = castFilter(filter, operation);

            if (castedFilter != null) {
                final Supplier<R> currentNextFunc = nextFunc;

                nextFunc = () -> castedFilter.filter(operation, currentNextFunc);
            }
        }

        return nextFunc.get();
    }

    /**
     * Finds the appropriate handler for the given operation.
     *
     * @param operation the operation to find a handler for
     * @return an Optional containing the handler if found, or empty if not found
     * @param <T> the type of operation
     * @param <R> the type of result returned by the operation
     */
    @SuppressWarnings("unchecked") // Safe cast based on type check
    public <T extends Operation<R>, R> Optional<OperationHandler<T, R>> getHandlerForOperation(Operation<R> operation) {
        return handlers
                .stream()
                .filter(handler -> handler.getOperationType().isAssignableFrom(operation.getClass()))
                .map(handler -> (OperationHandler<T, R>) handler)
                .findFirst();
    }

    @Nullable
    private <T extends Operation<R>, R> OperationFilter<T, R> castFilter(OperationFilter<?, ?> filter, Operation<?> operation) {
        if (filter.getOperationType().isAssignableFrom(operation.getClass())) {
            @SuppressWarnings("unchecked") // Safe
            OperationFilter<T, R> castedFilter = (OperationFilter<T, R>) filter;

            return castedFilter;
        }

        return null;
    }
}
