package me.zort.iis.server.iisserver.cqrs.operation.campaign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.domain.campaign.Campaign;

@Getter
@AllArgsConstructor
public class CreateCampaignOp implements Operation<Campaign> {
    private final String name;
    private final long themeId;

}
