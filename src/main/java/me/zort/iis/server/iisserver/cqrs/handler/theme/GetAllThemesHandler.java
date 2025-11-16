package me.zort.iis.server.iisserver.cqrs.handler.theme;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.theme.ThemeFacade;
import me.zort.iis.server.iisserver.cqrs.OperationHandler;
import me.zort.iis.server.iisserver.cqrs.operation.theme.GetAllThemesOp;
import me.zort.iis.server.iisserver.domain.campaign.Theme;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAllThemesHandler implements OperationHandler<GetAllThemesOp, Page<Theme>> {
    private final ThemeFacade themeFacade;

    @Override
    public Page<Theme> handle(GetAllThemesOp operation) {
        return themeFacade.getAllThemes(operation.getPageable());
    }

    @Override
    public Class<GetAllThemesOp> getOperationType() {
        return GetAllThemesOp.class;
    }
}
