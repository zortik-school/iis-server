package me.zort.iis.server.iisserver.domain.campaign.exception;

import lombok.Getter;

@Getter
public class CampaignNotFoundException extends RuntimeException {
    private final long campaignId;

    public CampaignNotFoundException(long campaignId) {
        super("Campaign with ID " + campaignId + " not found.");
        this.campaignId = campaignId;
    }
}
