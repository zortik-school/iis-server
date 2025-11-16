package me.zort.iis.server.iisserver.app.access;

import me.zort.iis.server.iisserver.domain.user.User;

public interface AccessStrategy {

    /**
     * Checks if the current user can view the theme with the given ID.
     *
     * @param themeId the ID of the theme to check access for
     * @param user the user whose access is being checked
     * @return true if the user can view the theme, false otherwise
     */
    boolean canViewTheme(long themeId, User user);
}
