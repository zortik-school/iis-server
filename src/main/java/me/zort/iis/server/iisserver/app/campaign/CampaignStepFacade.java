package me.zort.iis.server.iisserver.app.campaign;

import me.zort.iis.server.iisserver.domain.campaign.Step;
import me.zort.iis.server.iisserver.domain.campaign.exception.CampaignNotFoundException;
import me.zort.iis.server.iisserver.domain.campaign.exception.StepNotFoundException;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CampaignStepFacade {

    /**
     * Adds a step to a campaign based on the provided arguments.
     *
     * @param args the arguments required to add a step to a campaign
     */
    Step addStep(AddStepArgs args);

    /**
     * Deletes a step identified by the given step ID.
     *
     * @param stepId the ID of the step to be deleted
     * @throws StepNotFoundException If no step with the given ID exists
     */
    void deleteStep(long stepId);

    /**
     * Retrieves a step by its ID.
     *
     * @param stepId the ID of the step to retrieve
     * @return an Optional containing the step if it exists, or empty if not
     * @throws StepNotFoundException If no step with the given ID exists
     */
    Step getStep(long stepId);

    /**
     * Assigns a user to a step identified by the given step ID.
     *
     * @param stepId the ID of the step
     * @param userId the ID of the user to be assigned, or null to unassign
     */
    void assignUserToStep(long stepId, @Nullable Long userId);

    /**
     * Activates a step identified by the given step ID.
     *
     * @param stepId the ID of the step to be activated
     * @throws StepNotFoundException If no step with the given ID exists
     */
    void activateStep(long stepId);

    /**
     * Retrieves the active step for a specific campaign.
     *
     * @param campaignId the ID of the campaign
     * @return an Optional containing the active step if it exists, or empty if not
     * @throws CampaignNotFoundException If no campaign with the given ID exists
     */
    Optional<Step> getActiveStepForCampaign(long campaignId);

    /**
     * Retrieves the list of steps associated with a specific campaign.
     *
     * @param campaignId the ID of the campaign
     * @return the list of steps associated with the campaign
     */
    List<Step> getStepsForCampaign(long campaignId);

    /**
     * Retrieves a paginated list of campaign steps assigned to a specific user.
     *
     * @param userId the ID of the user
     * @param pageable pagination information
     * @return a page of campaign steps assigned to the specified user
     */
    Page<Step> getAssignedCampaignSteps(long userId, Pageable pageable);

    /**
     * Retrieves a paginated list of all campaign steps.
     *
     * @param pageable pagination information
     * @return a page of all campaign steps
     */
    Page<Step> getAllCampaignSteps(Pageable pageable);
}
