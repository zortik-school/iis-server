package me.zort.iis.server.iisserver.cqrs.handler.activity;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.AccessStrategy;
import me.zort.iis.server.iisserver.app.activity.ActivityFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.CommandHandler;
import me.zort.iis.server.iisserver.cqrs.operation.activity.DeleteActivityOp;
import me.zort.iis.server.iisserver.domain.access.exception.NoAccessException;
import me.zort.iis.server.iisserver.domain.campaign.Activity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteActivityHandler extends CommandHandler<DeleteActivityOp> {
    private final ActivityFacade activityFacade;
    private final AccessStrategy accessStrategy;
    private final UserProvider userProvider;

    @Override
    public void execute(DeleteActivityOp operation) {
        Activity activity = activityFacade.getActivity(operation.getActivityId());
        if (!accessStrategy
                .getCampaignStepAccessRole(activity.getStepId(), userProvider.getCurrentUser())
                .canManageActivities()) {
            throw new NoAccessException("User does not have access to delete activities in this step.");
        }

        activityFacade.deleteActivity(activity.getId());
    }

    @Override
    public Class<DeleteActivityOp> getOperationType() {
        return DeleteActivityOp.class;
    }
}
