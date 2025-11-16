package me.zort.iis.server.iisserver.cqrs.operation.theme;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.zort.iis.server.iisserver.cqrs.Operation;
import me.zort.iis.server.iisserver.domain.campaign.Theme;

@Getter
@AllArgsConstructor
public class GetThemeOp implements Operation<Theme> {
    private final long themeId;

}
