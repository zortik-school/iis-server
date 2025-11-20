package me.zort.iis.server.iisserver.domain.campaign.event;

public class CampaignDeletedEvent extends CampaignEvent {

    public CampaignDeletedEvent(long campaignId) {
        super(campaignId);
    }
}
