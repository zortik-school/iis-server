package me.zort.iis.server.iisserver.domain.campaign.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.domain.campaign.ActivityMembership;

@Getter
@AllArgsConstructor
public class ActivityMembershipImpl implements ActivityMembership {
    private final long activityId;
    private final long userId;

}
