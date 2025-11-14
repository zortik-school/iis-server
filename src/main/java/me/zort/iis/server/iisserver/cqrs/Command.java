package me.zort.iis.server.iisserver.cqrs;

/**
 * Marker interface for command operations that do not return a result.
 */
public interface Command extends Operation<Void> {
    // Marker interface
}
