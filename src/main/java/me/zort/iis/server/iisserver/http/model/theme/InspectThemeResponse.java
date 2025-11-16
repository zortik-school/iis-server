package me.zort.iis.server.iisserver.http.model.theme;

import lombok.Getter;
import me.zort.iis.server.iisserver.domain.campaign.Theme;

@Getter
public class InspectThemeResponse {
    private final long id;
    private final String name;
    private final String description;

    public InspectThemeResponse(Theme theme) {
        this.id = theme.getId();
        this.name = theme.getName();
        this.description = theme.getDescription();
    }
}
