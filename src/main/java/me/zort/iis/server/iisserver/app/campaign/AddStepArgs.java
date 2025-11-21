package me.zort.iis.server.iisserver.app.campaign;

import lombok.Builder;
import lombok.Getter;
import me.zort.iis.server.iisserver.domain.campaign.AddCampaignStepArgs;

@Getter
@Builder
public class AddStepArgs implements AddCampaignStepArgs {
    private final long campaignId;
    private final String name;

}
