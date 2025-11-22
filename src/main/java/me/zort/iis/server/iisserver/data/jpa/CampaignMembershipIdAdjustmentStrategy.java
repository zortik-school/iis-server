package me.zort.iis.server.iisserver.data.jpa;

import me.zort.iis.server.iisserver.data.IdAdjustmentStrategy;
import me.zort.iis.server.iisserver.data.jpa.entity.CampaignMembershipId;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CampaignMembershipIdAdjustmentStrategy implements IdAdjustmentStrategy<CampaignMembershipId> {

    @Override
    public CampaignMembershipId adjustIdBeforeInsert(@Nullable CampaignMembershipId suppliedId) {
        return suppliedId;
    }
}
