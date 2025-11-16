package me.zort.iis.server.iisserver.cqrs.handler.theme;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.theme.ThemeFacade;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.theme.InspectThemeOp;
import me.zort.iis.server.iisserver.domain.campaign.Theme;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InspectThemeHandler implements OperationHandler<InspectThemeOp, InspectThemeOp.Result> {
    private final ThemeFacade themeFacade;

    @Override
    public InspectThemeOp.Result handle(InspectThemeOp operation) {
        Theme theme = themeFacade.getTheme(operation.getThemeId());

        return new InspectThemeOp.Result(theme);
    }

    @Override
    public Class<InspectThemeOp> getOperationType() {
        return InspectThemeOp.class;
    }
}
