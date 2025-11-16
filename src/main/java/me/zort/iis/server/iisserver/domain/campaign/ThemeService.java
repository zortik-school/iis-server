package me.zort.iis.server.iisserver.domain.campaign;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ThemeService {

    /**
     * Retrieves a paginated list of all themes.
     *
     * @param pageable pagination information
     * @return a page of themes
     */
    Page<Theme> getAllThemes(Pageable pageable);
}
