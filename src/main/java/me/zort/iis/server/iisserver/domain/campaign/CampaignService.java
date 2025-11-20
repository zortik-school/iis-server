package me.zort.iis.server.iisserver.domain.campaign;

import me.zort.iis.server.iisserver.domain.campaign.exception.CampaignNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CampaignService {

    /**
     * Create a new campaign based on the provided arguments.
     *
     * @param args the arguments required to create a campaign
     * @return the created Campaign object
     */
    Campaign createCampaign(CreateCampaignArgs args);

    /**
     * Delete a campaign identified by the given campaign ID.
     *
     * @param campaignId the unique ID of the campaign to delete
     * @throws CampaignNotFoundException If no campaign with the given ID exists
     */
    void deleteCampaign(long campaignId);

    /**
     * Get a campaign by its ID.
     *
     * @param campaignId the ID of the campaign to retrieve
     * @return an Optional containing the Campaign if found, or empty if not found
     */
    Optional<Campaign> getCampaign(long campaignId);

    /**
     * Assign a user to a campaign.
     *
     * @param campaignId the ID of the campaign
     * @param userId the ID of the user to assign, or null to unassign
     * @throws CampaignNotFoundException If no campaign with the given ID exists
     */
    void assignUser(long campaignId, Long userId);

    /**
     * Get campaigns assigned to a specific user.
     *
     * @param userId the ID of the user
     * @param pageable pagination information
     * @return a list of campaigns assigned to the user
     */
    Page<Campaign> getAssignedCampaigns(long userId, Pageable pageable);

    /**
     * Check if any campaign of a specific theme is assigned to a user.
     *
     * @param themeId the ID of the theme
     * @param userId the ID of the user
     * @return true if any campaign of the theme is assigned to the user, false otherwise
     */
    boolean isAnyCampaignOfThemeAssignedToUser(long themeId, long userId);

    /**
     * Get all campaigns with pagination.
     *
     * @param pageable pagination information
     * @return a page of campaigns
     */
    Page<Campaign> getAllCampaigns(Pageable pageable);

    /**
     * Get all campaigns for a specific theme with pagination.
     *
     * @param themeId the ID of the theme
     * @param pageable pagination information
     * @return a page of campaigns for the specified theme
     */
    Page<Campaign> getAllCampaignsForTheme(long themeId, Pageable pageable);
}
