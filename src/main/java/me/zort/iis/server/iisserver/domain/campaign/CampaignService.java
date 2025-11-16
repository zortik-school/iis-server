package me.zort.iis.server.iisserver.domain.campaign;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CampaignService {

    // TODO

    /**
     * Get campaigns assigned to a specific user.
     *
     * @param userId the ID of the user
     * @return a list of campaigns assigned to the user
     */
    List<Campaign> getAssignedCampaigns(long userId);

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
