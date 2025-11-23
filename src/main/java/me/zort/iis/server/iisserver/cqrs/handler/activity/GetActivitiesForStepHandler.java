package me.zort.iis.server.iisserver.cqrs.handler.activity;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.AccessStrategy;
import me.zort.iis.server.iisserver.app.activity.ActivityFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.activity.GetActivitiesForStepOp;
import me.zort.iis.server.iisserver.domain.access.exception.NoAccessException;
import me.zort.iis.server.iisserver.domain.campaign.Activity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetActivitiesForStepHandler implements OperationHandler<GetActivitiesForStepOp, Page<Activity>> {
    private final ActivityFacade activityFacade;
    private final AccessStrategy accessStrategy;
    private final UserProvider userProvider;

    @Override
    public Page<Activity> handle(GetActivitiesForStepOp operation) {
        if (!accessStrategy
                .getCampaignStepAccessRole(operation.getStepId(), userProvider.getCurrentUser())
                .canManageActivities()) {
            throw new NoAccessException("User does not have permission to view activities for this step.");
        }

        return activityFacade.getActivitiesForStep(operation.getStepId(), operation.getPageable());
    }

    @Override
    public Class<GetActivitiesForStepOp> getOperationType() {
        return GetActivitiesForStepOp.class;
    }
}
