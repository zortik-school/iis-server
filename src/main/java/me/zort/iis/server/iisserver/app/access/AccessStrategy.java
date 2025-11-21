package me.zort.iis.server.iisserver.app.access;

import me.zort.iis.server.iisserver.domain.campaign.exception.StepNotFoundException;
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
     * Checks if the user can view assigned campaigns.
     *
     * @param user the user whose access is being checked
     * @return true if the user can view assigned campaigns, false otherwise
     */
    boolean canViewAssignedCampaigns(User user);

    /**
     * Checks if the user can view assigned campaign steps.
     *
     * @param user the user whose access is being checked
     * @return true if the user can view assigned campaign steps, false otherwise
     */
    boolean canViewAssignedCampaignSteps(User user);

    /**
     * Gets the access role of the user for the specified campaign.
     *
     * @param campaignId the ID of the campaign
     * @param user the user whose access role is being checked
     * @return the CampaignAccessRole of the user for the campaign
     */
    CampaignAccessRole getCampaignAccessRole(long campaignId, User user);

    /**
     * Gets the access role of the user for the specified campaign step.
     *
     * @param campaignStepId the ID of the campaign step
     * @param user the user whose access role is being checked
     * @return the CampaignStepAccessRole of the user for the campaign step
     * @throws StepNotFoundException if the campaign step does not exist
     */
    CampaignStepAccessRole getCampaignStepAccessRole(long campaignStepId, User user);
}
