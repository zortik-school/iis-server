package me.zort.iis.server.iisserver.cqrs.handler.campaign;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.AccessStrategy;
import me.zort.iis.server.iisserver.app.campaign.CampaignFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.campaign.GetAssignedCampaignsOp;
import me.zort.iis.server.iisserver.domain.access.exception.NoAccessException;
import me.zort.iis.server.iisserver.domain.campaign.Campaign;
import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAssignedCampaignsHandler implements OperationHandler<GetAssignedCampaignsOp, Page<Campaign>> {
    private final CampaignFacade campaignFacade;
    private final AccessStrategy accessStrategy;
    private final UserProvider userProvider;

    @Override
    public Page<Campaign> handle(GetAssignedCampaignsOp operation) {
        User user = userProvider.getCurrentUser();
        if (!accessStrategy.canViewAssignedCampaigns(user)) {
            throw new NoAccessException("User does not have access to view assigned campaigns.");
        }

        return campaignFacade.getAssignedCampaigns(user.getId(), operation.getPageable());
    }

    @Override
    public Class<GetAssignedCampaignsOp> getOperationType() {
        return GetAssignedCampaignsOp.class;
    }
}
