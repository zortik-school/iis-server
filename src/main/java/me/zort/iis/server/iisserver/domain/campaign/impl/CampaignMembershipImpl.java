package me.zort.iis.server.iisserver.domain.campaign.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.domain.campaign.CampaignMembership;

@Getter
@AllArgsConstructor
public class CampaignMembershipImpl implements CampaignMembership {
    private final long campaignId;
    private final long userId;

}
