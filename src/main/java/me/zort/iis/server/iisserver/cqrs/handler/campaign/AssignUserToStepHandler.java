package me.zort.iis.server.iisserver.cqrs.handler.campaign;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.AccessStrategy;
import me.zort.iis.server.iisserver.app.campaign.CampaignStepFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.CommandHandler;
import me.zort.iis.server.iisserver.cqrs.operation.campaign.AssignUserToStepOp;
import me.zort.iis.server.iisserver.domain.access.exception.NoAccessException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssignUserToStepHandler extends CommandHandler<AssignUserToStepOp> {
    private final CampaignStepFacade campaignStepFacade;
    private final AccessStrategy accessStrategy;
    private final UserProvider userProvider;

    @Override
    public void execute(AssignUserToStepOp operation) {
        if (!accessStrategy.getCampaignStepAccessRole(operation.getStepId(), userProvider.getCurrentUser()).canAssignStaff()) {
            throw new NoAccessException("User does not have access to assign staff to this campaign step.");
        }

        campaignStepFacade.assignUserToStep(operation.getStepId(), operation.getUserId());
    }

    @Override
    public Class<AssignUserToStepOp> getOperationType() {
        return AssignUserToStepOp.class;
    }
}
