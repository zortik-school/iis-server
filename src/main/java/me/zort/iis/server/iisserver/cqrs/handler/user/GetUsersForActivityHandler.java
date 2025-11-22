package me.zort.iis.server.iisserver.cqrs.handler.user;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.AccessStrategy;
import me.zort.iis.server.iisserver.app.activity.ActivityFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.user.GetUsersForActivityOp;
import me.zort.iis.server.iisserver.domain.access.exception.NoAccessException;
import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUsersForActivityHandler implements OperationHandler<GetUsersForActivityOp, Page<User>> {
    private final ActivityFacade activityFacade;
    private final AccessStrategy accessStrategy;
    private final UserProvider userProvider;

    @Override
    public Page<User> handle(GetUsersForActivityOp operation) {
        if (!accessStrategy
                .getActivityAccessRole(operation.getActivityId(), userProvider.getCurrentUser())
                .canView()) {
            throw new NoAccessException("User does not have permission to view users of this activity.");
        }

        return activityFacade.getMembersOfActivity(operation.getActivityId(), operation.getPageable());
    }

    @Override
    public Class<GetUsersForActivityOp> getOperationType() {
        return GetUsersForActivityOp.class;
    }
}
