package me.zort.iis.server.iisserver.app.access;

import me.zort.iis.server.iisserver.domain.access.exception.UserNotInCampaignZone;
import me.zort.iis.server.iisserver.domain.campaign.exception.ActivityNotFoundException;
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
     * Checks if the user can view assigned campaigns for him.
     *
     * @param user the user whose access is being checked
     * @return true if the user can view assigned campaigns, false otherwise
     */
    boolean canViewAssignedCampaigns(User user);

    /**
     * Checks if the user can view assigned campaign steps for him.
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

    /**
     * Gets the access role of the user for the specified activity.
     *
     * @param activityId the ID of the activity
     * @param user the user whose access role is being checked
     * @return the ActivityAccessRole of the user for the activity
     * @throws ActivityNotFoundException if the activity does not exist
     */
    ActivityAccessRole getActivityAccessRole(long activityId, User user);

    /**
     * Requires that the user is in the campaign zone.
     * This is used to ensure that the user has access to the campaign's restricted area.
     *
     * @param campaignZoneId the ID of the campaign
     * @param userId the ID of the user
     * @throws UserNotInCampaignZone if the user is not in the campaign zone
     */
    void requireUserInCampaignZone(long campaignZoneId, long userId);
}
