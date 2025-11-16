package me.zort.iis.server.iisserver.cqrs.handler.theme;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.theme.ThemeFacade;
import me.zort.iis.server.iisserver.app.theme.UpdateThemeArgs;
import me.zort.iis.server.iisserver.cqrs.CommandHandler;
import me.zort.iis.server.iisserver.cqrs.operation.theme.UpdateThemeOp;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateThemeHandler extends CommandHandler<UpdateThemeOp> {
    private final ThemeFacade themeFacade;

    @Override
    public void execute(UpdateThemeOp operation) {
        UpdateThemeArgs args = UpdateThemeArgs.builder()
                .themeId(operation.getThemeId())
                .name(operation.getName())
                .description(operation.getDescription())
                .build();

        themeFacade.updateTheme(args);
    }

    @Override
    public Class<UpdateThemeOp> getOperationType() {
        return UpdateThemeOp.class;
    }
}
