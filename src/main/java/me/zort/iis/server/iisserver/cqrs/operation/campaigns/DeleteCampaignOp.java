package me.zort.iis.server.iisserver.cqrs.operation.campaigns;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Command;

@Getter
@AllArgsConstructor
public class DeleteCampaignOp implements Command {
    private final long campaignId;

}
