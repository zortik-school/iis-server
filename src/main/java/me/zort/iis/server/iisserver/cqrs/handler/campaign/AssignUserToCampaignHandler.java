package me.zort.iis.server.iisserver.cqrs.handler.campaign;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.campaign.CampaignFacade;
import me.zort.iis.server.iisserver.cqrs.CommandHandler;
import me.zort.iis.server.iisserver.cqrs.operation.campaign.AssignUserToCampaignOp;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssignUserToCampaignHandler extends CommandHandler<AssignUserToCampaignOp> {
    private final CampaignFacade campaignFacade;

    @Override
    public void execute(AssignUserToCampaignOp operation) {
        campaignFacade.assignUser(operation.getCampaignId(), operation.getUserId());
    }

    @Override
    public Class<AssignUserToCampaignOp> getOperationType() {
        return AssignUserToCampaignOp.class;
    }
}
