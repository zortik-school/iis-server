package me.zort.iis.server.iisserver.cqrs.operation.campaign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Command;

@Getter
@AllArgsConstructor
public class RemoveUserFromCampaignOp implements Command {
    private final long campaignId;
    private final long userId;

}
