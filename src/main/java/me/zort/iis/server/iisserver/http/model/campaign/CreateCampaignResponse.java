package me.zort.iis.server.iisserver.http.model.campaign;

import lombok.Getter;
import me.zort.iis.server.iisserver.domain.campaign.Campaign;

@Getter
public class CreateCampaignResponse {
    private final long id;
    private final String name;
    private final long themeId;

    public CreateCampaignResponse(Campaign campaign) {
        this.id = campaign.getId();
        this.name = campaign.getName();
        this.themeId = campaign.getThemeId();
    }
}
