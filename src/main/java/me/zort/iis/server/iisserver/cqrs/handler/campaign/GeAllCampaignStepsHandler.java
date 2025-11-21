package me.zort.iis.server.iisserver.cqrs.handler.campaign;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.campaign.CampaignStepFacade;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.campaign.GetAllCampaignStepsOp;
import me.zort.iis.server.iisserver.domain.campaign.Step;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GeAllCampaignStepsHandler implements OperationHandler<GetAllCampaignStepsOp, Page<Step>> {
    private final CampaignStepFacade campaignStepFacade;

    @Override
    public Page<Step> handle(GetAllCampaignStepsOp operation) {
        return campaignStepFacade.getAllCampaignSteps(operation.getPageable());
    }

    @Override
    public Class<GetAllCampaignStepsOp> getOperationType() {
        return GetAllCampaignStepsOp.class;
    }
}
