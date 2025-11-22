package me.zort.iis.server.iisserver.cqrs.handler.activity;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.AccessStrategy;
import me.zort.iis.server.iisserver.app.activity.ActivityFacade;
import me.zort.iis.server.iisserver.app.activity.CreateActivityArgs;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.activity.CreateActivityOp;
import me.zort.iis.server.iisserver.domain.access.exception.NoAccessException;
import me.zort.iis.server.iisserver.domain.campaign.Activity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateActivityHandler implements OperationHandler<CreateActivityOp, Activity> {
    private final ActivityFacade activityFacade;
    private final AccessStrategy accessStrategy;
    private final UserProvider userProvider;

    @Override
    public Activity handle(CreateActivityOp operation) {
        if (!accessStrategy
                .getCampaignStepAccessRole(operation.getStepId(), userProvider.getCurrentUser())
                .canManageActivities()) {
            throw new NoAccessException("User does not have access to create activities in this step.");
        }

        CreateActivityArgs args = CreateActivityArgs.builder()
                .name(operation.getName())
                .description(operation.getDescription())
                .stepId(operation.getStepId())
                .startDate(operation.getStartDate())
                .endDate(operation.getEndDate())
                .build();
        return activityFacade.createActivity(args);
    }

    @Override
    public Class<CreateActivityOp> getOperationType() {
        return CreateActivityOp.class;
    }
}
