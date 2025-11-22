package me.zort.iis.server.iisserver.cqrs.handler.activity;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.AccessStrategy;
import me.zort.iis.server.iisserver.app.activity.ActivityFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.CommandHandler;
import me.zort.iis.server.iisserver.cqrs.operation.activity.AddUserToActivityOp;
import me.zort.iis.server.iisserver.domain.access.exception.NoAccessException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddUserToActivityHandler extends CommandHandler<AddUserToActivityOp> {
    private final ActivityFacade activityFacade;
    private final AccessStrategy accessStrategy;
    private final UserProvider userProvider;

    @Override
    public void execute(AddUserToActivityOp operation) {
        if (!accessStrategy
                .getActivityAccessRole(operation.getActivityId(), userProvider.getCurrentUser())
                .canAssignStaff()) {
            throw new NoAccessException("User does not have permission to add users to this activity.");
        }

        activityFacade.addUserToActivity(operation.getActivityId(), operation.getUserId());
    }

    @Override
    public Class<AddUserToActivityOp> getOperationType() {
        return AddUserToActivityOp.class;
    }
}
