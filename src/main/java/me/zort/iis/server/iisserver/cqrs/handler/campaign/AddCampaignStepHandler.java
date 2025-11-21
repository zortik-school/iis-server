package me.zort.iis.server.iisserver.cqrs.handler.campaign;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.campaign.AddStepArgs;
import me.zort.iis.server.iisserver.app.campaign.CampaignStepFacade;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.campaign.AddCampaignStepOp;
import me.zort.iis.server.iisserver.domain.campaign.Step;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddCampaignStepHandler implements OperationHandler<AddCampaignStepOp, Step> {
    private final CampaignStepFacade campaignStepFacade;

    @Override
    public Step handle(AddCampaignStepOp operation) {
        AddStepArgs args = AddStepArgs.builder()
                .name(operation.getName())
                .campaignId(operation.getCampaignId())
                .build();

        return campaignStepFacade.addStep(args);
    }

    @Override
    public Class<AddCampaignStepOp> getOperationType() {
        return AddCampaignStepOp.class;
    }
}
