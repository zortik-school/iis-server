package me.zort.iis.server.iisserver.cqrs.filter;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.cqrs.OperationFilter;

import java.util.List;

@RequiredArgsConstructor
public abstract class AggregateFilter implements OperationFilter<Operation<Object>, Object> {
    private final List<Class<? extends Operation<?>>> managedOperations;

    @Override
    public boolean handles(Operation<?> operation) {
        return managedOperations
                .stream()
                .anyMatch(opClass -> opClass.isAssignableFrom(operation.getClass()));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<Operation<Object>> getOperationType() {
        return (Class<Operation<Object>>) (Class<?>) Operation.class;
    }
}
