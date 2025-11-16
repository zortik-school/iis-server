package me.zort.iis.server.iisserver.cqrs.handler.theme;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.theme.ThemeFacade;
import me.zort.iis.server.iisserver.cqrs.CommandHandler;
import me.zort.iis.server.iisserver.cqrs.operation.theme.DeleteThemeOp;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteThemeHandler extends CommandHandler<DeleteThemeOp> {
    private final ThemeFacade themeFacade;

    @Override
    public void execute(DeleteThemeOp operation) {
        themeFacade.deleteTheme(operation.getThemeId());
    }

    @Override
    public Class<DeleteThemeOp> getOperationType() {
        return DeleteThemeOp.class;
    }
}
