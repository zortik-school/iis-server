package me.zort.iis.server.iisserver.startup.seeders;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.app.theme.CreateThemeArgs;
import me.zort.iis.server.iisserver.app.theme.ThemeFacade;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@RequiredArgsConstructor
public class ThemeSeeder implements Seeder {
    private final ThemeFacade themeFacade;

    @Override
    public void seed() {
        if (!themeFacade.getAllThemes(PageRequest.of(0, 1)).isEmpty()) {
            return;
        }

        CreateThemeArgs args = CreateThemeArgs.builder()
                .name("Just a theme")
                .description("This is really just a theme.")
                .build();
        themeFacade.createTheme(args);
    }
}
