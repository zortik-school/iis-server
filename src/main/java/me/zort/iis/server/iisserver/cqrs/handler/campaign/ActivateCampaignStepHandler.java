package me.zort.iis.server.iisserver.cqrs.handler.campaign;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.AccessStrategy;
import me.zort.iis.server.iisserver.app.campaign.CampaignStepFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.CommandHandler;
import me.zort.iis.server.iisserver.cqrs.operation.campaign.ActivateCampaignStepOp;
import me.zort.iis.server.iisserver.domain.access.exception.NoAccessException;
import me.zort.iis.server.iisserver.domain.campaign.Step;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActivateCampaignStepHandler extends CommandHandler<ActivateCampaignStepOp> {
    private final CampaignStepFacade campaignStepFacade;
    private final AccessStrategy accessStrategy;
    private final UserProvider userProvider;

    @Override
    public void execute(ActivateCampaignStepOp operation) {
        Step step = campaignStepFacade.getStep(operation.getStepId());
        if (!accessStrategy.canManageCampaign(step.getCampaignId(), userProvider.getCurrentUser())) {
            throw new NoAccessException("User has no access to activate steps on campaign " + step.getCampaignId());
        }

        campaignStepFacade.activateStep(operation.getStepId());
    }

    @Override
    public Class<ActivateCampaignStepOp> getOperationType() {
        return ActivateCampaignStepOp.class;
    }
}
