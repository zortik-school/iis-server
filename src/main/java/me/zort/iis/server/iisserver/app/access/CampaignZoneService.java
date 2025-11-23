package me.zort.iis.server.iisserver.app.access;

import me.zort.iis.server.iisserver.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CampaignZoneService {

    /**
     * Adds a user to the specified campaign zone.
     *
     * @param campaignZoneId the ID of the campaign zone
     * @param userId the ID of the user to be added
     */
    void addUserToCampaignZone(long campaignZoneId, long userId);

    /**
     * Removes a user from the specified campaign zone.
     *
     * @param campaignZoneId the ID of the campaign zone
     * @param userId the ID of the user to be removed
     */
    void removeUserFromCampaignZone(long campaignZoneId, long userId);

    Page<User> getMembersOfZone(long campaignZoneId, Pageable pageable);

    /**
     * Retrieves a list of campaign zone IDs associated with the specified user.
     *
     * @param userId the ID of the user
     * @return a list of campaign zone IDs for the user
     */
    List<Long> getCampaignZoneIdsForUser(long userId);

    /**
     * Retrieves the campaign zone ID associated with the specified campaign.
     *
     * @param campaignId the ID of the campaign
     * @return the campaign zone ID for the campaign
     */
    long getCampaignZoneIdForCampaign(long campaignId);

    /**
     * Retrieves the campaign zone ID associated with the specified campaign step.
     *
     * @param campaignStepId the ID of the campaign step
     * @return the campaign zone ID for the campaign step
     */
    long getCampaignZoneIdForCampaignStep(long campaignStepId);

    /**
     * Retrieves the campaign zone ID associated with the specified activity.
     *
     * @param activityId the ID of the activity
     * @return the campaign zone ID for the activity
     */
    long getCampaignZoneIdForActivity(long activityId);
}
