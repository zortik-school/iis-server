package me.zort.iis.server.iisserver.cqrs.operation.campaigns;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Command;

@Getter
@AllArgsConstructor
public class CreateCampaignOp implements Command {
    private final String name;
    private final long themeId;

}
