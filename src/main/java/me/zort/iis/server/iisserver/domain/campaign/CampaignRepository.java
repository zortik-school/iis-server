package me.zort.iis.server.iisserver.domain.campaign;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CampaignRepository {

    /**
     * Save a campaign.
     *
     * @param campaign the campaign to save
     * @return the saved campaign
     */
    Campaign save(Campaign campaign);

    /**
     * Delete a campaign by its ID.
     *
     * @param campaignId the ID of the campaign to delete
     */
    void deleteById(Long campaignId);

    /**
     * Find a campaign by its ID.
     *
     * @param campaignId the ID of the campaign
     * @return an Optional containing the found campaign, or empty if not found
     */
    Optional<Campaign> findById(Long campaignId);

    /**
     * Find all campaigns with pagination.
     *
     * @param pageable pagination information
     * @return a page of campaigns
     */
    Page<Campaign> findAll(Pageable pageable);

    /**
     * Find all campaigns by theme id.
     *
     * @param themeId the theme id
     * @param pageable pagination information
     * @return a page of campaigns
     */
    Page<Campaign> findAllByThemeId(long themeId, Pageable pageable);

    /**
     * Find all campaigns assigned to a specific user.
     *
     * @param userId the user id
     * @param pageable pagination information
     * @return a page of campaigns assigned to the user
     */
    Page<Campaign> findAllByAssignedUserId(long userId, Pageable pageable);
}
