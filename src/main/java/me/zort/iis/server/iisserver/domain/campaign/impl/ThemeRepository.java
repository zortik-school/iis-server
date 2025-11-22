package me.zort.iis.server.iisserver.domain.campaign.impl;

import me.zort.iis.server.iisserver.domain.campaign.Theme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ThemeRepository {

    /**
     * Saves a theme.
     *
     * @param theme the theme to be saved
     * @return the saved theme
     */
    Theme save(Theme theme);

    void deleteById(Long themeId);

    Optional<Theme> findById(Long themeId);

    /**
     * Finds all themes with pagination.
     *
     * @param pageable pagination information
     * @return a page of themes
     */
    Page<Theme> findAll(Pageable pageable);
}
