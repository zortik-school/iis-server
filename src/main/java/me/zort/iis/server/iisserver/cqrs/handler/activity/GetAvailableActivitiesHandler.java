package me.zort.iis.server.iisserver.cqrs.handler.activity;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.PrivilegesResolver;
import me.zort.iis.server.iisserver.app.activity.ActivityFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.activity.GetAvailableActivitiesOp;
import me.zort.iis.server.iisserver.domain.access.Privilege;
import me.zort.iis.server.iisserver.domain.access.exception.NoAccessException;
import me.zort.iis.server.iisserver.domain.campaign.Activity;
import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAvailableActivitiesHandler implements OperationHandler<GetAvailableActivitiesOp, Page<Activity>> {
    private final ActivityFacade activityFacade;
    private final PrivilegesResolver privilegesResolver;
    private final UserProvider userProvider;

    @Override
    public Page<Activity> handle(GetAvailableActivitiesOp operation) {
        User user = userProvider.getCurrentUser();
        if (!privilegesResolver
                .getGrantedPrivileges(user)
                .contains(Privilege.VIEW_AVAILABLE_ACTIVITIES)) {
            throw new NoAccessException("User does not have permission to view available activities.");
        }

        return activityFacade.getAvailableActivities(user.getId(), operation.getPageable());
    }

    @Override
    public Class<GetAvailableActivitiesOp> getOperationType() {
        return GetAvailableActivitiesOp.class;
    }
}
