package me.zort.iis.server.iisserver.cqrs.handler.activity;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.AccessStrategy;
import me.zort.iis.server.iisserver.app.activity.ActivityFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.CommandHandler;
import me.zort.iis.server.iisserver.cqrs.operation.activity.RemoveUserFromActivityOp;
import me.zort.iis.server.iisserver.domain.access.exception.NoAccessException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RemoveUserFromActivityHandler extends CommandHandler<RemoveUserFromActivityOp> {
    private final ActivityFacade activityFacade;
    private final AccessStrategy accessStrategy;
    private final UserProvider userProvider;

    @Override
    public void execute(RemoveUserFromActivityOp operation) {
        if (!accessStrategy
                .getActivityAccessRole(operation.getActivityId(), userProvider.getCurrentUser())
                .canAssignStaff()) {
            throw new NoAccessException("User does not have permission to remove users from this activity.");
        }

        activityFacade.removeUserFromActivity(operation.getActivityId(), operation.getUserId());
    }

    @Override
    public Class<RemoveUserFromActivityOp> getOperationType() {
        return RemoveUserFromActivityOp.class;
    }
}
