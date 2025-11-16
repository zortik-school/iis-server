package me.zort.iis.server.iisserver.cqrs.handler.campaign;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.campaign.CampaignFacade;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.campaigns.GetAllCampaignsOp;
import me.zort.iis.server.iisserver.domain.campaign.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAllCampaignsHandler implements OperationHandler<GetAllCampaignsOp, Page<Campaign>> {
    private final CampaignFacade campaignFacade;

    @Override
    public Page<Campaign> handle(GetAllCampaignsOp operation) {
        return campaignFacade.getAllCampaigns(operation.getPageable());
    }

    @Override
    public Class<GetAllCampaignsOp> getOperationType() {
        return GetAllCampaignsOp.class;
    }
}
