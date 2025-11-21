package me.zort.iis.server.iisserver.cqrs.handler.campaign;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.AccessStrategy;
import me.zort.iis.server.iisserver.app.campaign.CampaignStepFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.campaign.InspectCampaignStepOp;
import me.zort.iis.server.iisserver.domain.access.exception.NoAccessException;
import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class InspectCampaignStepHandler implements OperationHandler<InspectCampaignStepOp, InspectCampaignStepOp.Result> {
    private final CampaignStepFacade campaignStepFacade;
    private final AccessStrategy accessStrategy;
    private final UserProvider userProvider;

    @Override
    public InspectCampaignStepOp.Result handle(InspectCampaignStepOp operation) {
        User user = userProvider.getCurrentUser();
        if (!accessStrategy.getCampaignStepAccessRole(operation.getStepId(), user).canInspect()) {
            throw new NoAccessException("User does not have access to inspect this campaign step.");
        }

        Map<InspectCampaignStepOp.AccessPrivilege, Boolean> privileges = new HashMap<>();
        privileges.put(
                InspectCampaignStepOp.AccessPrivilege.ASSIGN_STAFF,
                accessStrategy.getCampaignStepAccessRole(operation.getStepId(), user).canAssignStaff());

        // Make it immutable
        privileges = Map.copyOf(privileges);

        return new InspectCampaignStepOp.Result(campaignStepFacade.getStep(operation.getStepId()), privileges);
    }

    @Override
    public Class<InspectCampaignStepOp> getOperationType() {
        return InspectCampaignStepOp.class;
    }
}
