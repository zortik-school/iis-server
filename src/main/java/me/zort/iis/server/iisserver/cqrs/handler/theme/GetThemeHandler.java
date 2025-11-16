package me.zort.iis.server.iisserver.cqrs.handler.theme;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.access.AccessStrategy;
import me.zort.iis.server.iisserver.app.theme.ThemeFacade;
import me.zort.iis.server.iisserver.app.user.UserProvider;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.theme.GetThemeOp;
import me.zort.iis.server.iisserver.domain.campaign.Theme;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetThemeHandler implements OperationHandler<GetThemeOp, Theme> {
    private final ThemeFacade themeFacade;
    private final AccessStrategy accessStrategy;
    private final UserProvider userProvider;

    @Override
    public Theme handle(GetThemeOp operation) {
        if (!accessStrategy.canViewTheme(operation.getThemeId(), userProvider.getCurrentUser())) {
            throw new SecurityException("Access denied to view theme with ID: " + operation.getThemeId());
        }

        return themeFacade.getTheme(operation.getThemeId());
    }

    @Override
    public Class<GetThemeOp> getOperationType() {
        return GetThemeOp.class;
    }
}
