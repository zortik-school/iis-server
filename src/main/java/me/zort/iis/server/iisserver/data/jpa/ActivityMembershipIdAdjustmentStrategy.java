package me.zort.iis.server.iisserver.data.jpa;

import me.zort.iis.server.iisserver.data.IdAdjustmentStrategy;
import me.zort.iis.server.iisserver.data.jpa.entity.ActivityMembershipId;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ActivityMembershipIdAdjustmentStrategy implements IdAdjustmentStrategy<ActivityMembershipId> {

    @Override
    public ActivityMembershipId adjustIdBeforeInsert(@Nullable ActivityMembershipId suppliedId) {
        return suppliedId;
    }
}
