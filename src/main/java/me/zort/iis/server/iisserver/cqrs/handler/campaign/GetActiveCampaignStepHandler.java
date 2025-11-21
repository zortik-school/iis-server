package me.zort.iis.server.iisserver.cqrs.handler.campaign;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.AccessStrategy;
import me.zort.iis.server.iisserver.app.campaign.CampaignStepFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.campaign.GetActiveCampaignStepOp;
import me.zort.iis.server.iisserver.domain.access.exception.NoAccessException;
import me.zort.iis.server.iisserver.domain.campaign.Step;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetActiveCampaignStepHandler implements OperationHandler<GetActiveCampaignStepOp, Optional<Step>> {
    private final CampaignStepFacade campaignStepFacade;
    private final AccessStrategy accessStrategy;
    private final UserProvider userProvider;

    @Override
    public Optional<Step> handle(GetActiveCampaignStepOp operation) {
        if (!accessStrategy.canManageCampaign(operation.getCampaignId(), userProvider.getCurrentUser())) {
            throw new NoAccessException("User does not have access to manage this campaign");
        }

        return campaignStepFacade.getActiveStepForCampaign(operation.getCampaignId());
    }

    @Override
    public Class<GetActiveCampaignStepOp> getOperationType() {
        return GetActiveCampaignStepOp.class;
    }
}
