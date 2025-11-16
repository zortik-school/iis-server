package me.zort.iis.server.iisserver.app.campaign;

import me.zort.iis.server.iisserver.domain.campaign.Campaign;

public interface CampaignFacade {

    /**
     * Creates a new campaign based on the provided arguments.
     *
     * @param args the arguments required to create a campaign
     * @return the created Campaign object
     */
    Campaign createCampaign(CreateCampaignArgs args);
}
