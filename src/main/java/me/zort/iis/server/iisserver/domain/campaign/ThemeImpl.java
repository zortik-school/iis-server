package me.zort.iis.server.iisserver.domain.campaign;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ThemeImpl implements Theme {
    private final long id;
    private final String name;
    private final String description;

}
