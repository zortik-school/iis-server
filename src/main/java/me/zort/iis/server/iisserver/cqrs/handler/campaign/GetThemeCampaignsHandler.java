package me.zort.iis.server.iisserver.cqrs.handler.campaign;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.campaign.CampaignFacade;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.campaigns.GetThemeCampaignsOp;
import me.zort.iis.server.iisserver.domain.campaign.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetThemeCampaignsHandler implements OperationHandler<GetThemeCampaignsOp, Page<Campaign>> {
    private final CampaignFacade campaignFacade;

    @Override
    public Page<Campaign> handle(GetThemeCampaignsOp operation) {
        return campaignFacade.getCampaignsForTheme(operation.getThemeId(), operation.getPageable());
    }

    @Override
    public Class<GetThemeCampaignsOp> getOperationType() {
        return GetThemeCampaignsOp.class;
    }
}
