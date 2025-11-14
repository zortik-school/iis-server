package me.zort.iis.server.iisserver.cqrs;

/**
 * Base class for command handlers.
 *
 * @param <T> the type of command this handler processes
 */
public abstract class CommandHandler<T extends Command> implements OperationHandler<T, Void> {

    /**
     * Execute the operation.
     *
     * @param operation the operation to execute
     */
    public abstract void execute(T operation);

    @Override
    public Void handle(T operation) {
        execute(operation);

        return null;
    }
}
