package me.zort.iis.server.iisserver.cqrs.handler.campaign;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.AccessStrategy;
import me.zort.iis.server.iisserver.app.campaign.CampaignStepFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.campaign.GetAssignedCampaignStepsOp;
import me.zort.iis.server.iisserver.domain.access.exception.NoAccessException;
import me.zort.iis.server.iisserver.domain.campaign.Step;
import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAssignedCampaignStepsHandler implements OperationHandler<GetAssignedCampaignStepsOp, Page<Step>> {
    private final CampaignStepFacade campaignStepFacade;
    private final AccessStrategy accessStrategy;
    private final UserProvider userProvider;

    @Override
    public Page<Step> handle(GetAssignedCampaignStepsOp operation) {
        User user = userProvider.getCurrentUser();
        if (!accessStrategy.canViewAssignedCampaignSteps(user)) {
            throw new NoAccessException("User does not have access to view assigned campaign steps.");
        }

        return campaignStepFacade.getAssignedCampaignSteps(user.getId(), operation.getPageable());
    }

    @Override
    public Class<GetAssignedCampaignStepsOp> getOperationType() {
        return GetAssignedCampaignStepsOp.class;
    }
}
