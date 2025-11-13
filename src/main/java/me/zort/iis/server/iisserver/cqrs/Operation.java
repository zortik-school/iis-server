package me.zort.iis.server.iisserver.cqrs;

/**
 * The CQRS Operation interface representing a command or query that returns a result of type R.
 *
 * @param <R> the type of result returned by the operation
 */
public interface Operation<R> {
    // Type marker interface for operations that return a result of type R
}
