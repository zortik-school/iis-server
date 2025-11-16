package me.zort.iis.server.iisserver.app.theme;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateThemeArgs implements me.zort.iis.server.iisserver.domain.campaign.UpdateThemeArgs {
    private final long themeId;
    private final String name;
    private final String description;

}
