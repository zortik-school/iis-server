package me.zort.iis.server.iisserver.domain.campaign;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CampaignService {

    /**
     * Create a new campaign based on the provided arguments.
     *
     * @param args the arguments required to create a campaign
     * @return the created Campaign object
     */
    Campaign createCampaign(CreateCampaignArgs args);

    /**
     * Get campaigns assigned to a specific user.
     *
     * @param userId the ID of the user
     * @param pageable pagination information
     * @return a list of campaigns assigned to the user
     */
    Page<Campaign> getAssignedCampaigns(long userId, Pageable pageable);

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
