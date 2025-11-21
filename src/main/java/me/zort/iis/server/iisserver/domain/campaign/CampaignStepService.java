package me.zort.iis.server.iisserver.domain.campaign;

import me.zort.iis.server.iisserver.domain.campaign.exception.StepNotFoundException;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CampaignStepService {

    /**
     * Add a new step to a campaign.
     *
     * @param args the arguments required to add a campaign step
     * @return the newly added Step
     */
    Step addStep(AddCampaignStepArgs args);

    /**
     * Delete a step by its ID.
     *
     * @param stepId the ID of the step to be deleted
     * @throws StepNotFoundException if the step with the given ID does not exist
     */
    void deleteStep(long stepId);

    /**
     * Assign a user to a step.
     *
     * @param stepId the ID of the step
     * @param userId the ID of the user to be assigned, or null to unassign
     * @throws StepNotFoundException if the step with the given ID does not exist
     */
    void assignUserToStep(long stepId, @Nullable Long userId);

    /**
     * Activate a step by its ID.
     *
     * @param stepId the ID of the step to be activated
     * @throws StepNotFoundException if the step with the given ID does not exist
     */
    void activateStep(long stepId);

    /**
     * Retrieve a step by its ID.
     *
     * @param stepId the ID of the step to be retrieved
     * @return an Optional containing the Step, or empty if not found
     */
    Optional<Step> getStep(long stepId);

    /**
     * Retrieve the active step for a given campaign.
     *
     * @param campaignId the ID of the campaign
     * @return an Optional containing the active Step, or empty if no steps are available
     */
    Optional<Step> getActiveStep(long campaignId);

    /**
     * Retrieve all steps for a given campaign.
     *
     * @param campaignId the ID of the campaign
     * @return a list of CampaignStepService objects associated with the campaign
     */
    List<Step> getAllStepsForCampaign(long campaignId);

    /**
     * Retrieve all steps assigned to a specific user with pagination.
     *
     * @param userId the ID of the user
     * @param pageable pagination information
     * @return a page of Step objects assigned to the user
     */
    Page<Step> getAssignedSteps(long userId, Pageable pageable);

    /**
     * Retrieve all steps with pagination.
     *
     * @param pageable pagination information
     * @return a page of Step objects
     */
    Page<Step> getAllSteps(Pageable pageable);
}
