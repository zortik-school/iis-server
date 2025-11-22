package me.zort.iis.server.iisserver.domain.campaign.exception;

import lombok.Getter;

@Getter
public class ActivityNotFoundException extends RuntimeException {
    private final long activityId;

    public ActivityNotFoundException(long activityId) {
        super("Activity with ID " + activityId + " not found.");
        this.activityId = activityId;
    }
}
