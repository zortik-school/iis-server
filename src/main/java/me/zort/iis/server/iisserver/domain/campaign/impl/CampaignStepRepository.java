package me.zort.iis.server.iisserver.domain.campaign.impl;

import me.zort.iis.server.iisserver.domain.campaign.Step;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface CampaignStepRepository {

    /**
     * Saves a Step to the repository.
     *
     * @param step the Step to be saved
     * @return the saved Step
     */
    Step save(Step step);

    /**
     * Checks if a Step exists in the repository by its ID.
     *
     * @param stepId the ID of the Step to be found
     * @return true if the Step exists, false otherwise
     */
    boolean existsById(Long stepId);

    /**
     * Deletes a Step from the repository by its ID.
     *
     * @param stepId the ID of the Step to be deleted
     */
    void deleteById(Long stepId);

    /**
     * Finds a Step by its ID.
     *
     * @param stepId the ID of the Step to be found
     * @return an Optional containing the found Step, or empty if not found
     */
    Optional<Step> findById(Long stepId);

    /**
     * Finds the active Step associated with a specific campaign ID.
     *
     * @param campaignId the ID of the campaign
     * @return an Optional containing the active Step, or empty if no active Step exists
     */
    Optional<Step> findByCampaignIdAndActiveTrue(long campaignId);

    /**
     * Finds all Steps associated with a specific campaign ID.
     *
     * @param campaignId the ID of the campaign
     * @return a list of Steps associated with the campaign ID
     */
    Stream<Step> findAllByCampaignId(long campaignId);

    /**
     * Finds all Steps associated with a list of campaign IDs.
     *
     * @param campaignIds the list of campaign IDs
     * @return a list of Steps associated with the campaign IDs
     */
    List<Step> findAllByCampaignIdIn(List<Long> campaignIds);

    /**
     * Retrieves all Steps in a paginated format.
     *
     * @param pageable pagination information
     * @return a Page containing the Steps
     */
    Page<Step> findAll(Pageable pageable);

    /**
     * Finds all Steps assigned to a specific user ID in a paginated format.
     *
     * @param userId the ID of the user
     * @param pageable pagination information
     * @return a Page containing the Steps assigned to the user
     */
    Page<Step> findAllByAssignedUserId(long userId, Pageable pageable);

    /**
     * Sets the active status to false for all Steps associated with a specific campaign ID.
     *
     * @param campaignId the ID of the campaign
     */
    void setActiveFalseByCampaignId(long campaignId);
}
