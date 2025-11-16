package me.zort.iis.server.iisserver.cqrs.handler.theme;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.theme.CreateThemeArgs;
import me.zort.iis.server.iisserver.app.theme.ThemeFacade;
import me.zort.iis.server.iisserver.cqrs.CommandHandler;
import me.zort.iis.server.iisserver.cqrs.operation.theme.CreateThemeOp;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateThemeHandler extends CommandHandler<CreateThemeOp> {
    private final ThemeFacade themeFacade;

    @Override
    public void execute(CreateThemeOp operation) {
        CreateThemeArgs args = CreateThemeArgs.builder()
                .name(operation.getName())
                .description(operation.getDescription())
                .build();

        themeFacade.createTheme(args);
    }

    @Override
    public Class<CreateThemeOp> getOperationType() {
        return CreateThemeOp.class;
    }
}
