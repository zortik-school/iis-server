package me.zort.iis.server.iisserver.cqrs.handler.campaign;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.AccessStrategy;
import me.zort.iis.server.iisserver.app.campaign.CampaignFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.campaign.InspectCampaignOp;
import me.zort.iis.server.iisserver.domain.access.exception.NoAccessException;
import me.zort.iis.server.iisserver.domain.campaign.Campaign;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InspectCampaignHandler implements OperationHandler<InspectCampaignOp, InspectCampaignOp.Result> {
    private final CampaignFacade campaignFacade;

    private final AccessStrategy accessStrategy;
    private final UserProvider userProvider;

    @Override
    public InspectCampaignOp.Result handle(InspectCampaignOp operation) {
        if (!accessStrategy.getCampaignAccessRole(operation.getCampaignId(), userProvider.getCurrentUser()).canInspect()) {
            throw new NoAccessException("User does not have access to inspect campaign with id " + operation.getCampaignId());
        }

        Campaign campaign = campaignFacade.getCampaign(operation.getCampaignId());

        return new InspectCampaignOp.Result(campaign);
    }

    @Override
    public Class<InspectCampaignOp> getOperationType() {
        return InspectCampaignOp.class;
    }
}
