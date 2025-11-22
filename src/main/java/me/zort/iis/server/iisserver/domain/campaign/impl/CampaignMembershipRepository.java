package me.zort.iis.server.iisserver.domain.campaign.impl;

import me.zort.iis.server.iisserver.domain.campaign.CampaignMembership;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CampaignMembershipRepository {

    /**
     * Saves a CampaignMembership entity.
     *
     * @param membership the CampaignMembership to save
     * @return the saved CampaignMembership
     */
    CampaignMembership save(CampaignMembership membership);

    /**
     * Deletes a CampaignMembership by campaign ID and user ID.
     *
     * @param campaignId the ID of the campaign
     * @param userId the ID of the user
     */
    void deleteByCampaignIdAndUserId(Long campaignId, Long userId);

    /**
     * Checks if a CampaignMembership exists for the given campaign ID and user ID.
     *
     * @param campaignId the ID of the campaign
     * @param userId the ID of the user
     * @return true if a CampaignMembership exists, false otherwise
     */
    boolean existsByCampaignIdAndUserId(Long campaignId, Long userId);

    /**
     * Finds all CampaignMemberships by campaign ID with pagination.
     *
     * @param campaignId the ID of the campaign
     * @param pageable pagination information
     * @return a page of CampaignMemberships
     */
    Page<CampaignMembership> findAllByCampaignId(Long campaignId, Pageable pageable);

    /**
     * Finds all CampaignMemberships by user ID.
     *
     * @param userId the ID of the user
     * @return a list of CampaignMemberships
     */
    List<CampaignMembership> findAllByUserId(Long userId);
}
