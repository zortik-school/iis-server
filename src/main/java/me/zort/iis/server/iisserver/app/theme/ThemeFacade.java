package me.zort.iis.server.iisserver.app.theme;

import me.zort.iis.server.iisserver.domain.campaign.Theme;
import me.zort.iis.server.iisserver.domain.campaign.exception.ThemeNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ThemeFacade {

    /**
     * Creates a new theme based on the provided arguments.
     *
     * @param args arguments for creating the theme
     * @return the created theme
     */
    Theme createTheme(CreateThemeArgs args);

    /**
     * Updates an existing theme with the provided arguments.
     *
     * @param args arguments for updating the theme
     * @return the updated theme
     * @throws ThemeNotFoundException if the theme with the given ID does not exist
     */
    Theme updateTheme(UpdateThemeArgs args);

    /**
     * Deletes a theme by its ID.
     *
     * @param themeId the ID of the theme to be deleted
     * @throws ThemeNotFoundException if the theme with the given ID does not exist
     */
    void deleteTheme(long themeId);

    /**
     * Retrieves a theme by its ID.
     *
     * @param themeId the ID of the theme to retrieve
     * @return the theme
     * @throws ThemeNotFoundException if the theme with the given ID does not exist
     */
    Theme getTheme(long themeId);

    /**
     * Retrieves a paginated list of all themes.
     *
     * @param pageable pagination information
     * @return a page of themes
     */
    Page<Theme> getAllThemes(Pageable pageable);
}
