package me.zort.iis.server.iisserver.cqrs.handler.activity;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.PrivilegesResolver;
import me.zort.iis.server.iisserver.app.activity.ActivityFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.activity.GetActivitiesOp;
import me.zort.iis.server.iisserver.domain.access.Privilege;
import me.zort.iis.server.iisserver.domain.access.exception.NoAccessException;
import me.zort.iis.server.iisserver.domain.campaign.Activity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetActivitiesHandler implements OperationHandler<GetActivitiesOp, Page<Activity>> {
    private final ActivityFacade activityFacade;
    private final PrivilegesResolver privilegesResolver;
    private final UserProvider userProvider;

    @Override
    public Page<Activity> handle(GetActivitiesOp operation) {
        if (!privilegesResolver
                .getGrantedPrivileges(userProvider.getCurrentUser()).contains(Privilege.MANAGE_ACTIVITIES)) {
            throw new NoAccessException("User does not have access to manage activities.");
        }

        return activityFacade.getAllActivities(operation.getPageable());
    }

    @Override
    public Class<GetActivitiesOp> getOperationType() {
        return GetActivitiesOp.class;
    }
}
