package me.zort.iis.server.iisserver.data.jpa;

import me.zort.iis.server.iisserver.data.IdAdjustmentStrategy;
import org.springframework.stereotype.Component;

@Component
public class JpaIdAdjustmentStrategy implements IdAdjustmentStrategy {

    @Override
    public Long adjustIdBeforeInsert(long suppliedId) {
        return suppliedId <= 0 ? null : suppliedId;
    }
}
