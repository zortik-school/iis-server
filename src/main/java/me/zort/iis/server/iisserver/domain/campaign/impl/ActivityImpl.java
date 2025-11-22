package me.zort.iis.server.iisserver.domain.campaign.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.zort.iis.server.iisserver.domain.campaign.Activity;
import me.zort.iis.server.iisserver.domain.campaign.ActivityState;

@Data
@AllArgsConstructor
public class ActivityImpl implements Activity {
    private final long id;
    private final String name;
    private final String description;
    private final long stepId;
    private final long startDate;
    private final long endDate;

    private ActivityState state;

}
