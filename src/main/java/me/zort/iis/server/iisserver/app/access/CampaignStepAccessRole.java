package me.zort.iis.server.iisserver.app.access;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CampaignStepAccessRole {
    ADMIN(true, true),
    EDITOR(true, false),
    NONE(false, false);

    private final boolean canInspect;
    private final boolean assignStaff;

    public boolean canInspect() {
        return canInspect;
    }

    public boolean canAssignStaff() {
        return assignStaff;
    }
}
