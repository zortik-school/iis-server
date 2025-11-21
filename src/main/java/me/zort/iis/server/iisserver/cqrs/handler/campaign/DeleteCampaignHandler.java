package me.zort.iis.server.iisserver.cqrs.handler.campaign;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.campaign.CampaignFacade;
import me.zort.iis.server.iisserver.cqrs.CommandHandler;
import me.zort.iis.server.iisserver.cqrs.operation.campaign.DeleteCampaignOp;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteCampaignHandler extends CommandHandler<DeleteCampaignOp> {
    private final CampaignFacade campaignFacade;

    @Override
    public void execute(DeleteCampaignOp operation) {
        campaignFacade.deleteCampaign(operation.getCampaignId());
    }

    @Override
    public Class<DeleteCampaignOp> getOperationType() {
        return DeleteCampaignOp.class;
    }
}
