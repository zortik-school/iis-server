package me.zort.iis.server.iisserver.app.access;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ActivityAccessRole {
    ADMIN(true, true, true, true),
    EXECUTOR(false, true, false, true),
    NONE(false, false, false, false);

    private final boolean canChangeState;
    private final boolean canCloseWithNote;
    private final boolean canAssignStaff;
    private final boolean canView;

    public boolean canChangeState() {
        return canChangeState;
    }

    public boolean canCloseWithNote() {
        return canCloseWithNote;
    }
    public boolean canAssignStaff() {
        return canAssignStaff;
    }

    public boolean canView() {
        return canView;
    }
}
