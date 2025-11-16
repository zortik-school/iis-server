package me.zort.iis.server.iisserver.cqrs.operation.theme;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Command;

@Getter
@AllArgsConstructor
public class CreateThemeOp implements Command {
    private final String name;
    private final String description;

}
