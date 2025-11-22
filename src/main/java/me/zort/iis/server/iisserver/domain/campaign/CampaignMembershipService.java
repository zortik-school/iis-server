package me.zort.iis.server.iisserver.domain.campaign;

import me.zort.iis.server.iisserver.domain.campaign.exception.UserAlreadyInCampaignException;
import me.zort.iis.server.iisserver.domain.campaign.exception.UserNotInCampaignException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CampaignMembershipService {

    /**
     * Adds a user to a campaign by creating a new CampaignMembership.
     *
     * @param campaignId The ID of the campaign to which the user will be added.
     * @param userId The ID of the user to be added to the campaign.
     * @return The created CampaignMembership object.
     * @throws UserAlreadyInCampaignException if the user is already a member of the campaign.
     */
    CampaignMembership addUserToCampaign(long campaignId, long userId);

    /**
     * Removes a user from a campaign by deleting the corresponding CampaignMembership.
     *
     * @param campaignId The ID of the campaign from which the user will be removed.
     * @param userId The ID of the user to be removed from the campaign.
     * @throws UserNotInCampaignException if the user is not a member of the campaign.
     */
    void removeUserFromCampaign(long campaignId, long userId);

    /**
     * Retrieves all campaign memberships for a specific user.
     *
     * @param userId The ID of the user whose campaign memberships are to be retrieved.
     * @return A list of CampaignMembership objects associated with the user.
     */
    List<CampaignMembership> getCampaignMembershipsOfUser(long userId);

    /**
     * Retrieves a paginated list of campaign memberships for a specific campaign.
     *
     * @param campaignId The ID of the campaign whose memberships are to be retrieved.
     * @param pageable Pagination information.
     * @return A page of CampaignMembership objects associated with the campaign.
     */
    Page<CampaignMembership> getCampaignMembershipsOfCampaign(long campaignId, Pageable pageable);
}
