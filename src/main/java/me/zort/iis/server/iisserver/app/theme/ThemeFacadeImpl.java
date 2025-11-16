package me.zort.iis.server.iisserver.app.theme;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.campaign.Theme;
import me.zort.iis.server.iisserver.domain.campaign.ThemeService;
import me.zort.iis.server.iisserver.domain.campaign.exception.ThemeNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThemeFacadeImpl implements ThemeFacade {
    private final ThemeService themeService;

    @Override
    public Theme createTheme(CreateThemeArgs args) {
        return themeService.createTheme(args);
    }

    @Override
    public Theme updateTheme(UpdateThemeArgs args) {
        return themeService.updateTheme(args);
    }

    @Override
    public void deleteTheme(long themeId) {
        themeService
                .getTheme(themeId)
                .ifPresentOrElse(
                        (theme) -> themeService.deleteTheme(theme.getId()),
                        () -> {
                            throw new ThemeNotFoundException(themeId);
                        });
    }

    @Override
    public Theme getTheme(long themeId) {
        return themeService
                .getTheme(themeId)
                .orElseThrow(() -> new ThemeNotFoundException(themeId));
    }

    @Override
    public Page<Theme> getAllThemes(Pageable pageable) {
        return themeService.getAllThemes(pageable);
    }
}
