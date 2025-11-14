package me.zort.iis.server.iisserver.domain.access.exception;

import lombok.Getter;
import me.zort.iis.server.iisserver.domain.access.Privilege;

@Getter
public class InsufficientPrivilegesException extends RuntimeException {
    private final Privilege missing;

    public InsufficientPrivilegesException(Privilege missing) {
        super("Insufficient privileges: missing " + missing.name());
        this.missing = missing;
    }
}
