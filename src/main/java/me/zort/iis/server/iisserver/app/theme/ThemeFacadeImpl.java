package me.zort.iis.server.iisserver.app.theme;

import lombok.RequiredArgsConstructor;
import me.zort.iis.server.iisserver.domain.campaign.Theme;
import me.zort.iis.server.iisserver.domain.campaign.ThemeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThemeFacadeImpl implements ThemeFacade {
    private final ThemeService themeService;

    @Override
    public Page<Theme> getAllThemes(Pageable pageable) {
        return themeService.getAllThemes(pageable);
    }
}
