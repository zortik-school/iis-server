package me.zort.iis.server.iisserver.domain.campaign.event;

public class CampaignCreatedEvent extends CampaignEvent {

    public CampaignCreatedEvent(long campaignId) {
        super(campaignId);
    }
}
