package me.zort.iis.server.iisserver.app.activity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateActivityArgs implements me.zort.iis.server.iisserver.domain.campaign.CreateActivityArgs {
    private String name;
    private String description;
    private long stepId;
    private long startDate;
    private long endDate;

}
