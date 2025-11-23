package me.zort.iis.server.iisserver.cqrs.handler.activity;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.AccessStrategy;
import me.zort.iis.server.iisserver.app.access.ActivityAccessRole;
import me.zort.iis.server.iisserver.app.activity.ActivityFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.activity.InspectActivityOp;
import me.zort.iis.server.iisserver.domain.campaign.Activity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class InspectActivityHandler implements OperationHandler<InspectActivityOp, InspectActivityOp.Result> {
    private final ActivityFacade activityFacade;
    private final AccessStrategy accessStrategy;
    private final UserProvider userProvider;

    @Override
    public InspectActivityOp.Result handle(InspectActivityOp operation) {
        Activity activity = activityFacade.getActivity(operation.getActivityId());
        ActivityAccessRole accessRole = accessStrategy
                .getActivityAccessRole(operation.getActivityId(), userProvider.getCurrentUser());

        Map<InspectActivityOp.AccessPrivilege, Boolean> accessPrivileges = new HashMap<>();
        accessPrivileges.put(InspectActivityOp.AccessPrivilege.CHANGE_STATE, accessRole.canChangeState());
        accessPrivileges.put(InspectActivityOp.AccessPrivilege.ASSIGN_STAFF, accessRole.canAssignStaff());

        return new InspectActivityOp.Result(activity, accessPrivileges);
    }

    @Override
    public Class<InspectActivityOp> getOperationType() {
        return InspectActivityOp.class;
    }
}
