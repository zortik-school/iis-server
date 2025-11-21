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

    /**
     * Checks if the current user can manage the campaign with the given ID.
     *
     * @param campaignId the ID of the campaign to check access for
     * @param user the user whose access is being checked
     * @return true if the user can manage the campaign, false otherwise
     */
    boolean canManageCampaign(long campaignId, User user);

    /**
     * Checks if the current user can manage the campaign step with the given ID.
     *
     * @param campaignStepId the ID of the campaign step to check access for
     * @param user the user whose access is being checked
     * @return true if the user can manage the campaign step, false otherwise
     */
    boolean canManageCampaignStep(long campaignStepId, User user);
}
