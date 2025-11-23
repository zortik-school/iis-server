package me.zort.iis.server.iisserver.cqrs.handler.activity;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.AccessStrategy;
import me.zort.iis.server.iisserver.app.activity.ActivityFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.activity.GetActivityOp;
import me.zort.iis.server.iisserver.domain.campaign.Activity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetActivityHandler implements OperationHandler<GetActivityOp, Activity> {
    private final ActivityFacade activityFacade;
    private final AccessStrategy accessStrategy;
    private final UserProvider userProvider;

    @Override
    public Activity handle(GetActivityOp operation) {
        if (!accessStrategy
                .getActivityAccessRole(operation.getActivityId(), userProvider.getCurrentUser())
                .canView()) {
            throw new RuntimeException("User does not have access to view this activity.");
        }

        return activityFacade.getActivity(operation.getActivityId());
    }

    @Override
    public Class<GetActivityOp> getOperationType() {
        return GetActivityOp.class;
    }
}
