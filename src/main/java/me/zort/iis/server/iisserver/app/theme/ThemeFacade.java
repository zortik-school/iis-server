package me.zort.iis.server.iisserver.app.theme;

import me.zort.iis.server.iisserver.domain.campaign.Theme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ThemeFacade {

    /**
     * Retrieves a paginated list of all themes.
     *
     * @param pageable pagination information
     * @return a page of themes
     */
    Page<Theme> getAllThemes(Pageable pageable);
}
