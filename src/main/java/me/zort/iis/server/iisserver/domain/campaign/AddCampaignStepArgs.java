package me.zort.iis.server.iisserver.domain.campaign;

public interface AddCampaignStepArgs {

    /**
     * The name of the step to be added.
     *
     * @return Name of the step.
     */
    String getName();

    /**
     * The ID of the campaign to which this step will be added.
     *
     * @return ID of the campaign.
     */
    long getCampaignId();
}
