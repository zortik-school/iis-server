package me.zort.iis.server.iisserver.app.campaign;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateCampaignArgs {
    private final String name;
    private final String description;

}
