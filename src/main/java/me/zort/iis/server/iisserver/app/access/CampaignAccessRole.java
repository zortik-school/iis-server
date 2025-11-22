package me.zort.iis.server.iisserver.app.access;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CampaignAccessRole {
    ADMIN(true, true, true, true, true),
    EDITOR(true, true, false, false, true),
    NONE(false, false, false, false, false);

    private final boolean manageSteps;
    private final boolean inspect;
    private final boolean editDetails;
    private final boolean assignStaff;
    private final boolean manageMembers;

    public boolean canManageSteps() {
        return manageSteps;
    }

    public boolean canInspect() {
        return inspect;
    }

    public boolean canEditDetails() {
        return editDetails;
    }

    public boolean canAssignStaff() {
        return assignStaff;
    }

    public boolean canManageMembers() {
        return manageMembers;
    }
}
