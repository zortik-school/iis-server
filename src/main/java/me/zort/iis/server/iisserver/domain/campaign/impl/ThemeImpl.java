package me.zort.iis.server.iisserver.domain.campaign.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.zort.iis.server.iisserver.domain.campaign.Theme;

@Data
@AllArgsConstructor
public class ThemeImpl implements Theme {
    private final long id;

    private String name;
    private String description;

}
