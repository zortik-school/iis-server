package me.zort.iis.server.iisserver.domain.campaign.event;

import lombok.Getter;

@Getter
public class CampaignUserAssignedEvent extends CampaignEvent {
    private final long userId;

    public CampaignUserAssignedEvent(long campaignId, long userId) {
        super(campaignId);
        this.userId = userId;
    }
}
