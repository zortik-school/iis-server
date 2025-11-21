package me.zort.iis.server.iisserver.cqrs.operation.campaign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.domain.campaign.Campaign;

@Getter
@AllArgsConstructor
public class InspectCampaignOp implements Operation<InspectCampaignOp.Result> {
    private final long campaignId;

    @Getter
    @AllArgsConstructor
    public static class Result {
        private final Campaign campaign;

    }
}
