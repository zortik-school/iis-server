package me.zort.iis.server.iisserver.domain.user.exception;

import lombok.Getter;

@Getter
public class UserConflictException extends RuntimeException {
    private final UserConflictException.ConflictType conflictType;

    @Getter
    public enum ConflictType {
        USERNAME_TAKEN("Username taken.");

        private final String message;

        ConflictType(String message) {
            this.message = message;
        }
    }

    public UserConflictException(ConflictType type) {
        super(type.getMessage());
        this.conflictType = type;
    }
}
