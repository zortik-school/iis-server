package me.zort.iis.server.iisserver.app.access;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CampaignStepAccessRole {
    ADMIN(true, true, true),
    EDITOR(true, false, true),
    NONE(false, false, false);

    private final boolean canInspect;
    private final boolean assignStaff;
    private final boolean manageActivities;

    public boolean canInspect() {
        return canInspect;
    }

    public boolean canAssignStaff() {
        return assignStaff;
    }

    public boolean canManageActivities() {
        return manageActivities;
    }
}
