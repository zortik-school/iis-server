package me.zort.iis.server.iisserver.domain.campaign;

import me.zort.iis.server.iisserver.domain.campaign.exception.StepNotFoundException;

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
}
