package me.zort.iis.server.iisserver.app.campaign;

import me.zort.iis.server.iisserver.domain.campaign.Step;
import me.zort.iis.server.iisserver.domain.campaign.exception.CampaignNotFoundException;
import me.zort.iis.server.iisserver.domain.campaign.exception.StepNotFoundException;

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
}
