package me.zort.iis.server.iisserver.app.access;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ActivityAccessRole {
    ADMIN(true, true, true, true, true),
    EXECUTOR(false, true, false, false, true),
    VIEWER(false, false, false, false, true),
    NONE(false, false, false, false, false);

    private final boolean canOpen;
    private final boolean canCloseWithNote;
    private final boolean canCloseForcibly;
    private final boolean canAssignStaff;
    private final boolean canView;

    public boolean canOpen() {
        return canOpen;
    }

    public boolean canCloseWithNote() {
        return canCloseWithNote;
    }

    public boolean canCloseForcibly() {
        return canCloseForcibly;
    }

    public boolean canAssignStaff() {
        return canAssignStaff;
    }

    public boolean canView() {
        return canView;
    }
}
