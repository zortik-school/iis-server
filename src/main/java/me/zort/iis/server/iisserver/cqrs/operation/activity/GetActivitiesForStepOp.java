package me.zort.iis.server.iisserver.cqrs.operation.activity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.domain.campaign.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
public class GetActivitiesForStepOp implements Operation<Page<Activity>> {
    private final long stepId;
    private final Pageable pageable;

}
