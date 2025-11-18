package me.zort.iis.server.iisserver.app.campaign;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateCampaignArgs implements me.zort.iis.server.iisserver.domain.campaign.CreateCampaignArgs {
    private final String name;
    private final long themeId;

}
