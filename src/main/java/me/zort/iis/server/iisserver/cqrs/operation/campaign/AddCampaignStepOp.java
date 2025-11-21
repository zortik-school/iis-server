package me.zort.iis.server.iisserver.cqrs.operation.campaign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.domain.campaign.Step;

@Getter
@AllArgsConstructor
public class AddCampaignStepOp implements Operation<Step> {
    private final long campaignId;
    private final String name;

}
