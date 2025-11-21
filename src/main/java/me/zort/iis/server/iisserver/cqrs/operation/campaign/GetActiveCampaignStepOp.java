package me.zort.iis.server.iisserver.cqrs.operation.campaign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.domain.campaign.Step;

import java.util.Optional;

@Getter
@AllArgsConstructor
public class GetActiveCampaignStepOp implements Operation<Optional<Step>> {
    private final long campaignId;

}
