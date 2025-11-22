package me.zort.iis.server.iisserver.http.model.activity;

import lombok.Getter;
import me.zort.iis.server.iisserver.domain.campaign.Activity;
import me.zort.iis.server.iisserver.domain.campaign.ActivityState;

@Getter
public class ActivityModel {
    private final long id;
    private final String name;
    private final String description;
    private final long stepId;
    private final ActivityState state;

    public ActivityModel(Activity activity) {
        this.id = activity.getId();
        this.name = activity.getName();
        this.description = activity.getDescription();
        this.stepId = activity.getStepId();
        this.state = activity.getState();
    }
}
