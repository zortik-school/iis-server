package me.zort.iis.server.iisserver.cqrs.operation.activity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.domain.campaign.Activity;

@Getter
@AllArgsConstructor
public class CreateActivityOp implements Operation<Activity> {
    private final String name;
    private final String description;
    private final long stepId;
    private final long startDate;
    private final long endDate;

}
