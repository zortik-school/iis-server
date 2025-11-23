package me.zort.iis.server.iisserver.cqrs.handler.activity;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.AccessStrategy;
import me.zort.iis.server.iisserver.app.activity.ActivityFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.CommandHandler;
import me.zort.iis.server.iisserver.cqrs.operation.activity.OpenActivityOp;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpenActivityHandler extends CommandHandler<OpenActivityOp> {
    private final ActivityFacade activityFacade;
    private final AccessStrategy accessStrategy;
    private final UserProvider userProvider;

    @Override
    public void execute(OpenActivityOp operation) {
        if (!accessStrategy
                .getActivityAccessRole(operation.getActivityId(), userProvider.getCurrentUser())
                .canChangeState()) {
            throw new RuntimeException("User does not have access to open this activity.");
        }

        activityFacade.openActivity(operation.getActivityId());
    }

    @Override
    public Class<OpenActivityOp> getOperationType() {
        return OpenActivityOp.class;
    }
}
