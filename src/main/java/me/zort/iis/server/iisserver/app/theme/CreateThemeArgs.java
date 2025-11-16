package me.zort.iis.server.iisserver.app.theme;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateThemeArgs implements me.zort.iis.server.iisserver.domain.campaign.CreateThemeArgs {
    private final String name;
    private final String description;

}
