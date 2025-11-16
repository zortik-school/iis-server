package me.zort.iis.server.iisserver.cqrs.operation.theme;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Command;

@Getter
@AllArgsConstructor
public class UpdateThemeOp implements Command {
    private final long themeId;
    private final String name;
    private final String description;

}
