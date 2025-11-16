package me.zort.iis.server.iisserver.domain.campaign;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ThemeImpl implements Theme {
    private final long id;

    private String name;
    private String description;

}
