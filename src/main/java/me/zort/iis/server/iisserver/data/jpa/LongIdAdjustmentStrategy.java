package me.zort.iis.server.iisserver.data.jpa;

import me.zort.iis.server.iisserver.data.IdAdjustmentStrategy;
import org.springframework.stereotype.Component;

@Component
public class LongIdAdjustmentStrategy implements IdAdjustmentStrategy<Long> {

    @Override
    public Long adjustIdBeforeInsert(Long suppliedId) {
        if (suppliedId == null) {
            return null;
        }

        return suppliedId <= 0 ? null : suppliedId;
    }
}
