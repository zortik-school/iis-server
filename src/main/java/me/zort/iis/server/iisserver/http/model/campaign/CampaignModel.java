package me.zort.iis.server.iisserver.http.model.campaign;

import lombok.Getter;
import me.zort.iis.server.iisserver.domain.campaign.Campaign;

@Getter
public class CampaignModel {
    private final long id;
    private final String name;

    public CampaignModel(Campaign campaign) {
        this.id = campaign.getId();
        this.name = campaign.getName();
    }
}
