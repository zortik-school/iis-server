package me.zort.iis.server.iisserver.app.campaign;

import me.zort.iis.server.iisserver.domain.campaign.Campaign;
import me.zort.iis.server.iisserver.domain.campaign.exception.CampaignNotFoundException;
import me.zort.iis.server.iisserver.domain.user.exception.UserNotFoundException;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CampaignFacade {

    /**
     * Creates a new campaign based on the provided arguments.
     *
     * @param args the arguments required to create a campaign
     * @return the created Campaign object
     */
    Campaign createCampaign(CreateCampaignArgs args);

    /**
     * Deletes a campaign identified by the given campaign ID.
     *
     * @param campaignId the unique ID of the campaign to delete
     * @throws CampaignNotFoundException If no campaign with the given ID exists
     */
    void deleteCampaign(long campaignId);

    /**
     * Retrieves a campaign by its unique ID.
     *
     * @param campaignId the unique ID of the campaign to retrieve
     * @return the Campaign object
     * @throws CampaignNotFoundException If no campaign with the given ID exists
     */
    Campaign getCampaign(long campaignId);

    /**
     * Assigns a user to a campaign. If userId is null, unassigns any user from the campaign.
     *
     * @param campaignId the ID of the campaign
     * @param userId the ID of the user to assign, or null to unassign
     * @throws CampaignNotFoundException If no campaign with the given ID exists
     * @throws UserNotFoundException If no user with the given ID exists
     */
    void assignUser(long campaignId, @Nullable Long userId);

    /**
     * Retrieves a paginated list of campaigns assigned to a specific user.
     *
     * @param userId the ID of the user
     * @param pageable pagination information
     * @return a page of campaigns assigned to the specified user
     */
    Page<Campaign> getAssignedCampaigns(long userId, Pageable pageable);

    /**
     * Retrieves a paginated list of campaigns associated with a specific theme.
     *
     * @param themeId the ID of the theme
     * @param pageable pagination information
     * @return a page of campaigns associated with the specified theme
     */
    Page<Campaign> getCampaignsForTheme(long themeId, Pageable pageable);

    /**
     * Retrieves a paginated list of all campaigns.
     *
     * @param pageable pagination information
     * @return a page of all campaigns
     */
    Page<Campaign> getAllCampaigns(Pageable pageable);
}
