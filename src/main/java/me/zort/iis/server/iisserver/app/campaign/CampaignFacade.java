package me.zort.iis.server.iisserver.app.campaign;

import me.zort.iis.server.iisserver.domain.campaign.Campaign;
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
