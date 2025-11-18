package me.zort.iis.server.iisserver.cqrs.handler.campaign;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.campaign.CampaignFacade;
import me.zort.iis.server.iisserver.app.campaign.CreateCampaignArgs;
import me.zort.iis.server.iisserver.cqrs.CommandHandler;
import me.zort.iis.server.iisserver.cqrs.operation.campaigns.CreateCampaignOp;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCampaignHandler extends CommandHandler<CreateCampaignOp> {
    private final CampaignFacade campaignFacade;

    @Override
    public void execute(CreateCampaignOp operation) {
        CreateCampaignArgs args = CreateCampaignArgs.builder()
                .name(operation.getName())
                .themeId(operation.getThemeId())
                .build();

        campaignFacade.createCampaign(args);
    }

    @Override
    public Class<CreateCampaignOp> getOperationType() {
        return CreateCampaignOp.class;
    }
}
